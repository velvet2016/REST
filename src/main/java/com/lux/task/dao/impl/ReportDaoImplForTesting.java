package com.lux.task.dao.impl;

import com.lux.task.dao.ReportDao;
import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportLine;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.lux.task.Constants.SIMPLE_DATE_FORMAT;
import static com.lux.task.Constants.TIMEZONE;


public class ReportDaoImplForTesting implements ReportDao {

    private Date getDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss z").parse("01.01.2016 01:00:00 "+TIMEZONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public List<ReportLine> getReport(int numberOfMonths) {
        List<ReportLine> list = new ArrayList<>();
        for (int i = 1; i <= numberOfMonths; i++) {
            list.add(getReportLine(i));
        }
        return list;
    }

    private Product getProduct(int i) {
        return new Product(i,"mock_product_"+i,i);
    }
    private Purchase getPurchase(int i){
        return new Purchase(i,getProduct(i),i, getDate() );
    }
    private ReportLine getReportLine(int i){
        return new ReportLine(getPurchase(i));
    }

}
