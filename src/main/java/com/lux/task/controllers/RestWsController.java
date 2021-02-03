package com.lux.task.controllers;


import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.PurchaseService;
import com.lux.task.dao.services.ReportService;
import com.lux.task.exceptions.IncompatibleReportParameterException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
public class RestWsController {

    @Autowired
    private ReportService reportService;


    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "report", method = RequestMethod.GET, /*headers="Accept=application/json",*/ produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportLine> getReport(@RequestParam("monthNumber") int monthNumber)  {
        if (monthNumber < 1) {
            throw new IncompatibleReportParameterException();
        }
        return reportService.getReport(monthNumber);
    }

    @RequestMapping(value = "purchases", method = RequestMethod.POST, headers = "Accept=application/json",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPurchases(@RequestBody List<Purchase> purchases) {
        purchaseService.storePurchases(purchases);
    }

    @RequestMapping(value = "purchases", method = RequestMethod.GET, headers = "Accept=application/json",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Purchase> getPurchases() {
        return purchaseService.getAll();
    }

    @RequestMapping(value = "products", method = RequestMethod.GET, headers = "Accept=application/json",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @RequestMapping(value = "product", method = RequestMethod.POST, headers = "Accept=application/json",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postProduct(@RequestBody Product product) {
        productService.add(product);
    }



    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IncompatibleReportParameterException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class})
    public String handleException(Exception exception) {
        return exception.getMessage();
    }


}
