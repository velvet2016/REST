package com.lux.task.controllers;


import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportArguments;
import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.PurchaseService;
import com.lux.task.dao.services.ReportService;
import com.lux.task.exceptions.IncompatibleReportParameterException;
import com.lux.task.validators.ReportArgumentsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestWsController {

    @Autowired
    private ReportService reportService;


    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/rest/report", method = RequestMethod.GET, /*headers="Accept=application/json",*/ produces = {"application/json"})
    public List<ReportLine> getReport(@RequestParam("monthNumber") int monthNumber)  {
        if (monthNumber < 1) {
            throw new IncompatibleReportParameterException();
        }
        return reportService.getReport(monthNumber);
    }
    @RequestMapping(value = "/rest/products", method = RequestMethod.GET, /*headers="Accept=application/json",*/ produces = {"application/json"})
    public List<String> getProducts() {
        return productService.getListForUiSelect();
    }

    @RequestMapping(value = "/rest/add_purchases", method = RequestMethod.POST, headers="Accept=application/json",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Purchase> addPurchases(@RequestBody List<Purchase> purchases)  {
        return purchaseService.storePurchases(purchases);
    }



    @ExceptionHandler({IncompatibleReportParameterException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(Exception exception) {
        return exception.getCause().toString();
    }


}
