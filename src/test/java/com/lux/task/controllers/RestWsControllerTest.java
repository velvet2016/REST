package com.lux.task.controllers;


import com.lux.task.dao.ReportDao;
import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.lux.task.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml","/testContext.xml"})
@WebAppConfiguration
public  class RestWsControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    private ReportDao reportDao;

    private static final int REPORT_LIST_SIZE = 10;

    private Product getProduct(int i) {
        return new Product(i,"mock_product_"+i,i);
    }
    private Purchase getPurchase(int i){
        return new Purchase(i,getProduct(i),i, getDate() );
    }
    private ReportLine getReportLine(int i){
        return new ReportLine(getPurchase(i));
    }
    private Date getDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse("01.01.2016 01:00:00 "+TIMEZONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    private List<ReportLine> getMockedReport(int numberOfMonths) {
        List<ReportLine> list = new ArrayList<>();
        for (int i = 1; i <= numberOfMonths; i++) {
            list.add(getReportLine(i));
        }
        return list;
    }
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        List<ReportLine> mockedReport = getMockedReport(REPORT_LIST_SIZE);
        when(reportDao.getReport(REPORT_LIST_SIZE)).thenReturn(mockedReport);
    }

    @Test
    public void reportParamLessThan1() throws Exception {
        MvcResult result = mockMvc.perform(get("/rest/report").param("monthNumber", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals(REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER,content);
    }

    @Test
    public void testLastLineInReport() throws Exception {
        List<ReportLine> report = reportDao.getReport(REPORT_LIST_SIZE);
        int last = REPORT_LIST_SIZE - 1;
        mockMvc.perform(get("/rest/report").param("monthNumber", String.valueOf(REPORT_LIST_SIZE)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[" + last + "].purchaseDate", is(SIMPLE_DATE_FORMAT.format(report.get(last).getPurchaseDate()))))
                .andExpect(jsonPath("$[" + last + "].id", is(report.get(last).getId())))
                .andExpect(jsonPath("$[" + last + "].product.id", is(report.get(last).getProduct().getId())))
                .andExpect(jsonPath("$[" + last + "].product.name", is(report.get(last).getProduct().getName())))
                .andExpect(jsonPath("$[" + last + "].product.price", is(report.get(last).getProduct().getPrice())))
                .andExpect(jsonPath("$[" + last + "].sum", is(report.get(last).getSum())));
    }



}
