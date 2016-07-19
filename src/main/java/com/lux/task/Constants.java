package com.lux.task;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Constants {
    public static final String REPORT_PARAMETER_SHOULD_BE_POSITIVE_INTEGER = "Report parameter should be positive integer";
    public static final String UI_DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_FORMAT = "dd.MM.yyyy hh:mm:ss z";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
    public static final String TIMEZONE = "UTC";

    static
    {
        SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
    }
}
