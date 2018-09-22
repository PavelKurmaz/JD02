package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PermissionConverter implements Converter<PermissionDTO, Permission> {

    @Override
    public Permission toEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        return permission;
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
