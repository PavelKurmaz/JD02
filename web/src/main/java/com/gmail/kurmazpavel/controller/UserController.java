package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.config.PageProperties;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.AddressService;
import com.gmail.kurmazpavel.service.RoleService;
import com.gmail.kurmazpavel.UserPrincipal;
import com.gmail.kurmazpavel.service.UserService;
import com.gmail.kurmazpavel.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserValidator validator;
    @Autowired
    private PageProperties pageProperties;

    @GetMapping
    public String index(){
        return pageProperties.getUserProfilePagePath();
    }

    @GetMapping(value = "/admin")
    public String admin(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO user = userService.read(userPrincipal.getId());
        modelMap.addAttribute("admin", user);
        return pageProperties.getAdminProfilePath();
    }

    @GetMapping(value = "/profile")
    public String profile(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO user = userService.read(userPrincipal.getId());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("address", user.getAddress());
        return pageProperties.getUserProfilePagePath();
    }

    @GetMapping(value = "/create")
    public String create(ModelMap modelMap) {
        List<RoleDTO> roles = roleService.getAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getUserSignupPagePath();
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") UserDTO user,
                         @RequestParam(value = "select") String role,
                         BindingResult result,
                         ModelMap modelMap) {
        validator.validate(user, result);
        if (result.hasErrors()){
            modelMap.addAttribute("user", user);
            return pageProperties.getUserSignupPagePath();
        }
        userService.create(user, role);
        user = userService.readByEmail(user.getEmail());
        AddressDTO address = user.getAddress();
        modelMap.addAttribute("address", address);
        return pageProperties.getUserAddressPagePath();
    }

    @PostMapping(value = "/address/{id}")
    public String address(@ModelAttribute("address") AddressDTO address,
                          @PathVariable("id") Long id,
                          BindingResult result,
                          ModelMap modelMap) {
        addressService.update(address);
        return pageProperties.getUserLoginPagePath();
    }

    @GetMapping(value = "/login")
    public String login() {
        return pageProperties.getUserLoginPagePath();
    }

    @PostMapping(value = "/login")
    public String postLogin(){
            return pageProperties.getUserProfilePagePath();
    }
}
