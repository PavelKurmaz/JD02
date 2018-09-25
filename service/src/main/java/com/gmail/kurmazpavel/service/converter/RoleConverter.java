package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.Admin;
import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("roleConverter")
public class RoleConverter implements Converter<RoleDTO, Role> {
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("adminConverter")
    private Converter<AdminDTO, Admin> adminConverter;
    @Autowired
    @Qualifier("permissionConverter")
    private Converter<PermissionDTO, Permission> permissionConverter;

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
