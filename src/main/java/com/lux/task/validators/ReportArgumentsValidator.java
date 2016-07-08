package com.lux.task.validators;

import com.lux.task.dao.models.ReportArguments;
import org.springframework.validation.Errors;

public class ReportArgumentsValidator {
    private ReportArguments reportArgs;
    private Errors res;

    public ReportArgumentsValidator(ReportArguments reportArgs, Errors res) {
        this.reportArgs = reportArgs;
        this.res = res;
    }
    public void validate(){
        if (reportArgs.getMonthCount()<=0){
            res.rejectValue("monthCount", "negative or zero", "number of months should be integer positive");
        }
    }
}
