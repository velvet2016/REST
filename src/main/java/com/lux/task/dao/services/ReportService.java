package com.lux.task.dao.services;

import com.lux.task.dao.impl.ReportDaoImpl;
import com.lux.task.dao.models.ReportLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportService {

    @Autowired
    private ReportDaoImpl dao;

    public List<ReportLine> getReport(int numberOfMonths){
        return dao.getReport(numberOfMonths);
    }

}
