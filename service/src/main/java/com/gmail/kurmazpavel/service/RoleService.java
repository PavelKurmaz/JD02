package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import java.util.List;

public interface RoleService {

    RoleDTO read(Long entityID);
    RoleDTO readByRole(String roleName);
    RoleDTO create(RoleDTO roleDTO);
    RoleDTO update(RoleDTO roleDTO);
    RoleDTO delete (RoleDTO roleDTO);
    List<RoleDTO> getAll();
    List<PermissionDTO> getPermissions(Long id);
}