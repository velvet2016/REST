package com.lux.task.controllers;


import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.PurchaseService;
import com.lux.task.dao.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestWsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReportService reportService;
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/rest/report/{monthNumber}", method = RequestMethod.GET, headers="Accept=application/json", produces = {"application/json"})
    public List<ReportLine> getReport(@PathVariable int monthNumber) {
        return reportService.getReport(monthNumber);
    }


}
