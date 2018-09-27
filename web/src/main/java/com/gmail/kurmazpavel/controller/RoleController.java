package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("role", new RoleDTO());
        return "createrole";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute ("role") RoleDTO role,
                         BindingResult result,
                         ModelMap modelMap) {
        roleService.create(role);
        return "admlogin";
    }
}
