package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
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
