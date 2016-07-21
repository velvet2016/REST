package com.lux.task;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Component
public class Config {
    @PostConstruct
    public void setTimeZone(){
        System.out.println("setting timezone to UTC");
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("curernt time zone: " + TimeZone.getDefault());
    }
}
