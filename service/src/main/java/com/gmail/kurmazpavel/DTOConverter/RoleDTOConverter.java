package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {

    @Override
    public RoleDTO toDTO(Role entity) {
        RoleDTO role = new RoleDTO();
        role.setId(entity.getId());
        role.setRole(entity.getRole());
        return role;
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
