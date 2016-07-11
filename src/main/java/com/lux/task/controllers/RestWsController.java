package com.lux.task.controllers;


import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.PurchaseService;
import com.lux.task.dao.services.ReportService;
import com.lux.task.exceptions.IncompatibleReportParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class RestWsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReportService reportService;
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/rest/report", method = RequestMethod.GET, /*headers="Accept=application/json",*/ produces = {"application/json"})
    public List<ReportLine> getReport(@RequestParam("monthNumber") int monthNumber)  {
        if (monthNumber < 1) {
            throw new IncompatibleReportParameterException();
        }
        return reportService.getReport(monthNumber);
    }
    @RequestMapping(value = "/rest/add_purchase", method = RequestMethod.POST, headers="Accept=application/json",
            produces = {"application/json"}, consumes = {"application/json"})
    public boolean addPurchase(@Valid @RequestBody Purchase purchase/*, BindingResult result*/)  {
       // if (result.hasErrors()){ return result.getAllErrors();}
        return purchaseService.storePurchase(purchase);
    }

    @ExceptionHandler(value = IncompatibleReportParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleIncompatibleReportParameterException(Exception e){
        return e.getMessage();
    }


}
