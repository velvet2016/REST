package com.lux.task.dao.models;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;

public class ReportArguments {

    @Min(1)
    private int monthCount;

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }
}
