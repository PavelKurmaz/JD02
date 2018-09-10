package com.gmail.kurmazpavel.beans.dto;

import java.util.Objects;

public class RoleDTO {
    private long id;
    private String role;

    public RoleDTO() {}

    public RoleDTO(long id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return id == roleDTO.id &&
                Objects.equals(role, roleDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
