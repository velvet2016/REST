package com.lux.task.exceptions;

public class IncompatibleReportParameterException extends RuntimeException {
    public IncompatibleReportParameterException() {
        super("Report parameter should be positive integer");
    }
}
