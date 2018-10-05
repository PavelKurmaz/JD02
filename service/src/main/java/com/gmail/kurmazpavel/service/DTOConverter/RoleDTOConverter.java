package com.gmail.kurmazpavel.service.DTOConverter;

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

@Component("roleDTOConverter")
public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter;
    @Autowired
    @Qualifier("permissionDTOConverter")
    private DTOConverter<PermissionDTO, Permission> permissionDTOConverter;

    @Override
    public RoleDTO toDTO(Role entity) {
        RoleDTO role = new RoleDTO();
        role.setId(entity.getId());
        role.setRole(entity.getRole());
        role.setUsers(userDTOConverter.toDTOList(entity.getUsers()));
        role.setPermissions(permissionDTOConverter.toDTOList(entity.getPermissions()));
        return role;
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
