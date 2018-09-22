package com.gmail.kurmazpavel.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
    private Long id;
    private String role;
    private List<UserDTO> users = new ArrayList<>();
    private List<AdminDTO> admins = new ArrayList<>();
    private List<PermissionDTO> permissions = new ArrayList<>();

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public List<AdminDTO> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminDTO> admins) {
        this.admins = admins;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

}
