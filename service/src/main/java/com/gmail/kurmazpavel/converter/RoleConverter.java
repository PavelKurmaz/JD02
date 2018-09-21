package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter implements Converter<RoleDTO, Role> {
    private UserConverter userConverter = new UserConverter();
    private AdminConverter adminConverter = new AdminConverter();
    private PermissionConverter permissionConverter = new PermissionConverter();

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setRole(dto.getRole());
        role.setAdmins(adminConverter.toEntityList(dto.getAdmins()));
        role.setUsers(userConverter.toEntityList(dto.getUsers()));
        role.setPermissions(permissionConverter.toEntityList(dto.getPermissions()));
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
