package com.lux.task.exceptions;

import com.lux.task.constants;

public class IncompatibleReportParameterException extends RuntimeException {

    public IncompatibleReportParameterException() {
        super(constants.REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER);
    }
}
