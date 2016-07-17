package com.lux.task.controllers;


import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.List;

import static com.lux.task.Constants.REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@WebAppConfiguration
public  class RestWsControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReportService reportService;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFirstApple() throws Exception {
        mockMvc.perform(get("/rest/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0]", is("apple")));
    }


    @Test
    public void reportParamLessThan1() throws Exception {
        ResultActions ra = mockMvc.perform(get("/rest/report").param("monthNumber","0"));
        ra.andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0]", is(REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER)));
    }

    @Test
    public void testFirstLineInReport() throws Exception {
        int numberOfMonths = 1;
        List<ReportLine> report = reportService.getReport(numberOfMonths);
        if (!report.isEmpty()) {
            ResultActions ra = mockMvc.perform(get("/rest/report").param("monthNumber", String.valueOf(numberOfMonths)));
            ra.andExpect(status().isOk())
                    .andExpect(content().contentType(contentType))
                    .andExpect(jsonPath("$[0]", is(report.get(0))));
        }
    }



}
