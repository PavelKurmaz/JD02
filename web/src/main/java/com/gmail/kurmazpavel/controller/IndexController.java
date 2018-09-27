package com.gmail.kurmazpavel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    public String login(@RequestParam(value = "select") String value) {
        if (value.equalsIgnoreCase("admin"))
            return "admlogin";
        else
            return "login";
    }
}

