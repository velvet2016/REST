package com.lux.task.exceptions;

import com.lux.task.Constants;

public class IncompatibleReportParameterException extends RuntimeException {

    public IncompatibleReportParameterException() {
        super(Constants.REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER);
    }
}
