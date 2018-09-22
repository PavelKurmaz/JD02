package com.gmail.kurmazpavel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan (basePackages = {"com.gmail.kurmazpavel.controller"})
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers (ViewResolverRegistry registry) {
        registry
                .jsp()
                .prefix("/WEB-INF/pages/")
                .suffix(".jsp");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
