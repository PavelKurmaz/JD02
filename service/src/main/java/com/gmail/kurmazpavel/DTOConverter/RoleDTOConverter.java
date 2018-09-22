package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {
    private UserDTOConverter userDTOConverter = new UserDTOConverter();
    private AdminDTOConverter adminDTOConverter = new AdminDTOConverter();
    private PermissionDTOConverter permissionDTOConverter = new PermissionDTOConverter();

    @Override
    public RoleDTO toDTO(Role entity) {
        RoleDTO role = new RoleDTO();
        role.setId(entity.getId());
        role.setRole(entity.getRole());
        role.setAdmins(adminDTOConverter.toDTOList(entity.getAdmins()));
        role.setUsers(userDTOConverter.toDTOList(entity.getUsers()));
        role.setPermissions(permissionDTOConverter.toDTOList(entity.getPermissions()));
        return role;
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
