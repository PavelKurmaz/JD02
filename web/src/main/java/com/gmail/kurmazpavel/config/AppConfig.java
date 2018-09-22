package com.gmail.kurmazpavel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.gmail.kurmazpavel.dao",
                                "com.gmail.kurmazpavel.service",
                                "com.gmail.kurmazpavel.controller",
                                "com.gmail.kurmazpavel.config"})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
