package com.gmail.kurmazpavel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class PageProperties {

    @Autowired
    private Environment environment;
    private String errorsPagePath;

    @PostConstruct
    public void initialize() {
        this.errorsPagePath = environment.getProperty("errors.page.path");
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }
}
