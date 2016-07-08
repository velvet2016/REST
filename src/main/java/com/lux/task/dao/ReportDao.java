package com.lux.task.dao;

import com.lux.task.dao.models.ReportLine;

import java.util.List;

public interface ReportDao {
    List<ReportLine> getReport(int numberOfMonths);
}
