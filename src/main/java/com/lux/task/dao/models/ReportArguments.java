package com.lux.task.dao.models;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;

public class ReportArguments {
    public ReportArguments() {
    }

    public ReportArguments(Integer monthCount) {
        this.monthCount = monthCount;
    }

    @Min(1)
    private Integer monthCount;

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }
}
