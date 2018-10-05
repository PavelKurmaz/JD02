package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.config.PageProperties;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.service.PermissionService;
import com.gmail.kurmazpavel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PageProperties pageProperties;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String index(ModelMap modelMap){
        List<PermissionDTO> permissions = permissionService.getAll();
        modelMap.addAttribute("permissions", permissions);
        return pageProperties.getPermissionsPagePath();
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String create(ModelMap modelMap){
        modelMap.addAttribute("permission", new PermissionDTO());
        return pageProperties.getPermissionsCreatePagePath();
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String create(@ModelAttribute("permission") PermissionDTO permission, ModelMap modelMap){
        permissionService.create(permission);
        List<PermissionDTO> permissions = permissionService.getAll();
        modelMap.addAttribute("permissions", permissions);
        return pageProperties.getPermissionsPagePath();
    }

    @PostMapping(value = "/edit/{id}")
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String edit(@PathVariable("id")Long id,
                       ModelMap modelMap){
        PermissionDTO permission = permissionService.read(id);
        List<RoleDTO> roles = permissionService.getRolesByName(permission.getName());
        List<RoleDTO> allRoles = roleService.getAll();
        allRoles.removeAll(roles);
        modelMap.addAttribute("permission", permission);
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("allroles", allRoles);
        return pageProperties.getPermissionEditPagePath();
    }

    @PostMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String delete(@PathVariable("id") Long id,
                         ModelMap modelMap){
        PermissionDTO permission = permissionService.read(id);
        permissionService.delete(permission);
        List<PermissionDTO> permissions = permissionService.getAll();
        modelMap.addAttribute("permissions", permissions);
        return pageProperties.getPermissionsPagePath();
    }

    @PostMapping(value = "/edit/deleteRole/{id}")
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String deleteRole(@PathVariable("id") Long id,
                             ModelMap modelMap,
                             @RequestParam(value = "permissionId") String permissionId){
        permissionService.deleteRole(Long.valueOf(permissionId), id);
        PermissionDTO permission = permissionService.read(Long.valueOf(permissionId));
        List<RoleDTO> roles = permissionService.getRolesByName(permission.getName());
        List<RoleDTO> allRoles = roleService.getAll();
        allRoles.removeAll(roles);
        modelMap.addAttribute("permission", permission);
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("allroles", allRoles);
        return pageProperties.getPermissionEditPagePath();
    }

    @PostMapping(value = "/edit/addRole")
    @PreAuthorize("hasAnyAuthority('Edit Permissions')")
    public String addRole(ModelMap modelMap,
                          @RequestParam(value = "permissionId") String permissionId,
                          @RequestParam(value = "select") String roleName){
        RoleDTO role = roleService.readByRole(roleName);
        permissionService.addRole(Long.valueOf(permissionId), role.getId());
        PermissionDTO permission = permissionService.read(Long.valueOf(permissionId));
        List<RoleDTO> roles = permissionService.getRolesByName(permission.getName());
        List<RoleDTO> allRoles = roleService.getAll();
        allRoles.removeAll(roles);
        modelMap.addAttribute("permission", permission);
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("allroles", allRoles);
        return pageProperties.getPermissionEditPagePath();
    }
}
