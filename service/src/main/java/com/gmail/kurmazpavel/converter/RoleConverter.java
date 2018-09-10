package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter implements Converter<RoleDTO, Role> {
    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setRole(dto.getRole());
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
