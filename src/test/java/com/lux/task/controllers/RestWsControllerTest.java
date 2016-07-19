package com.lux.task.controllers;


import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.ReportService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.List;

import static com.lux.task.Constants.REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER;
import static com.lux.task.Constants.SIMPLE_DATE_FORMAT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
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
    private ReportService reportService;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
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
    public void testFirstLineInReport() throws Exception {
        int numberOfMonths = 10;
        List<ReportLine> report = reportService.getReport(numberOfMonths);
        if (!report.isEmpty()) {
            ResultActions ra = mockMvc.perform(get("/rest/report").param("monthNumber", String.valueOf(numberOfMonths)));
            ra.andExpect(status().isOk())
                    .andExpect(content().contentType(contentType))
                    .andExpect(jsonPath("$[0].purchaseDate", is(SIMPLE_DATE_FORMAT.format(report.get(0).getPurchaseDate()))))
                    .andExpect(jsonPath("$[0].id", is(report.get(0).getId())))
                    .andExpect(jsonPath("$[0].product.id", is(report.get(0).getProduct().getId())))
                    .andExpect(jsonPath("$[0].product.name", is(report.get(0).getProduct().getName())))
                    .andExpect(jsonPath("$[0].product.price", is(report.get(0).getProduct().getPrice())))
                    .andExpect(jsonPath("$[0].sum", is(report.get(0).getSum())));

        }
    }



}
