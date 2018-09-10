package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.PermissionDTO;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;

import java.util.List;

public interface PermissionService {

    PermissionDTO read(Long entityID);
    PermissionDTO readByName(String name);
    PermissionDTO deleteRole(Long entityID, Long roleId);
    PermissionDTO addRole(Long entityID, Long roleId);
    void create(PermissionDTO permissionDTO);
    PermissionDTO update(PermissionDTO permissionDTO);
    PermissionDTO delete(PermissionDTO permissionDTO);
    List<PermissionDTO> getAll();
    List<RoleDTO> getRolesByName(String name);
}
