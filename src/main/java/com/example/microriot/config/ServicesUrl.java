package com.example.microriot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServicesUrl {
    public static ServicesUrl BASE_URL;

    @PostConstruct
    public void init() {
        BASE_URL = this;
    }

}
