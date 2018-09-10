package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Permission;
import com.gmail.kurmazpavel.beans.dto.PermissionDTO;

import java.util.List;
import java.util.stream.Collectors;

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
