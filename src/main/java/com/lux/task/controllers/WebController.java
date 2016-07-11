package com.lux.task.controllers;


import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportArguments;
import com.lux.task.dao.models.ReportLine;
import com.lux.task.dao.services.ProductService;
import com.lux.task.dao.services.PurchaseService;
import com.lux.task.dao.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReportService reportService;
    @Autowired
    private PurchaseService purchaseService;

/*    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, "purchaseDate", new CustomDateEditor(dateFormat, false));
    }*/

    @RequestMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/add_purchase",method = RequestMethod.GET)
    public ModelAndView addPurchase(@ModelAttribute("purchase") Purchase purchase) {
        return getAddPurchaseModelAndView();
    }

    @RequestMapping(value = "/purchase_added", method = RequestMethod.POST)
    public ModelAndView purchaseAdded(@Valid @ModelAttribute("purchase") Purchase purchase, BindingResult result) {
        if (result.hasErrors()) {
            return getAddPurchaseModelAndView();
        } else {
            ModelAndView purchaseAdded = new ModelAndView("purchase_added");
            purchaseService.storePurchase(purchase);
            return purchaseAdded;
        }
    }

    @RequestMapping(value = "/get_report", method = RequestMethod.GET)
    public ModelAndView getReport() {
        return new ModelAndView("get_report","reportArgs", new ReportArguments());
    }

    @RequestMapping(value = "/report_list_purchases", method = RequestMethod.POST)
    public ModelAndView reportList(@Valid @ModelAttribute("reportArgs") ReportArguments reportArgs, BindingResult result) {
        if (result.hasErrors()){
            return new ModelAndView("get_report");
        }else {
            ModelAndView report = new ModelAndView("report_list_purchases");
            List<ReportLine> reportLines = reportService.getReport(reportArgs.getMonthCount());
            report.addObject("report", reportLines);
            return report;
        }
    }

    private ModelAndView getAddPurchaseModelAndView() {
        ModelAndView addPurchase = new ModelAndView("add_purchase");
        addPurchase.addObject("products", productService.getListForUiSelect());
        return addPurchase;
    }


}
