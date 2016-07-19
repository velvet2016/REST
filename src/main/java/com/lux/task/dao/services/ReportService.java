package com.lux.task.dao.services;

import com.lux.task.dao.ReportDao;
import com.lux.task.dao.impl.ReportDaoImpl;
import com.lux.task.dao.models.ReportLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportDao dao;

    public List<ReportLine> getReport(int numberOfMonths){
        return dao.getReport(numberOfMonths);
    }

}
