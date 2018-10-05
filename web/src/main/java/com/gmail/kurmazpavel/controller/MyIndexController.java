package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.config.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myindex")
public class MyIndexController {

    @Autowired
    private PageProperties pageProperties;

    @GetMapping
    public String index() {
        return pageProperties.getStartPagePath();
    }
}
