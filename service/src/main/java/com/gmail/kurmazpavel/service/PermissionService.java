package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;

import java.util.List;

public interface PermissionService {

    PermissionDTO read(Long entityID);
    PermissionDTO readByName(String name);
    PermissionDTO deleteRole(Long entityID, Long roleId);
    PermissionDTO addRole(Long entityID, Long roleId);
    PermissionDTO create(PermissionDTO permissionDTO);
    PermissionDTO update(PermissionDTO permissionDTO);
    PermissionDTO delete(PermissionDTO permissionDTO);
    List<PermissionDTO> getAll();
    List<RoleDTO> getRolesByName(String name);
}
