package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.service.AdminService;
import com.gmail.kurmazpavel.service.RoleService;
import com.gmail.kurmazpavel.util.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminValidator validator;

    @GetMapping
    public String index(){
        return "admin";
    }

    @GetMapping(value = "/create")
    public String create(ModelMap modelMap) {
        List<RoleDTO> roles = roleService.getAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("admin", new AdminDTO());
        return "admsignup";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("admin") AdminDTO admin,
                         @RequestParam(value = "select") String role,
                         BindingResult result,
                         ModelMap modelMap) {
        validator.validate(admin, result);
        if (result.hasErrors()){
            modelMap.addAttribute("admin", admin);
            return "admsignup";
        }
        service.create(admin, role);
        return "admlogin";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "login") String login,
                        @RequestParam(value = "password") String password,
                        ModelMap modelMap) {
        AdminDTO admin = service.readByLogin(login);
        if (password.equalsIgnoreCase(admin.getPassword())) {
            modelMap.addAttribute("admin", admin);
            return "admin";
        }
            return "admlogin";
    }
}
