package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.Address;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.AddressService;
import com.gmail.kurmazpavel.service.UserService;
import com.gmail.kurmazpavel.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserValidator validator;

    @GetMapping
    public String index(){
        return "profile";
    }

    @GetMapping(value = "/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return "signup";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") UserDTO user,
                         BindingResult result,
                         ModelMap modelMap) {
        validator.validate(user, result);
        if (result.hasErrors()){
            modelMap.addAttribute("user", user);
            return "signup";
        }
        userService.create(user);
        user = userService.readByEmail(user.getEmail());
        AddressDTO address = user.getAddress();
        modelMap.addAttribute("address", address);
        return "address";
    }

    @PostMapping(value = "/address/{id}")
    public String address(@ModelAttribute("address") AddressDTO address,
                          @PathVariable("id") Long id,
                          BindingResult result,
                          ModelMap modelMap) {
        addressService.update(address);
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "login") String login,
                        @RequestParam(value = "password") String password,
                        ModelMap modelMap) {
        UserDTO user = userService.readByLogin(login);
        if (password.equalsIgnoreCase(user.getPassword())) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("address", user.getAddress());
            return "profile";
        }
        return "login";
    }

}
