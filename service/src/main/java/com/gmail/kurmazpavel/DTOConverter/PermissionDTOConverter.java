package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Permission;
import com.gmail.kurmazpavel.beans.dto.PermissionDTO;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionDTOConverter implements DTOConverter<PermissionDTO, Permission> {

    @Override
    public PermissionDTO toDTO(Permission entity) {
        PermissionDTO permission = new PermissionDTO();
        permission.setId(entity.getId());
        permission.setName(entity.getName());
        return permission;
    }

    @Override
    public List<PermissionDTO> toDTOList(List<Permission> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
