package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.service.PermissionService;
import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.service.converter.Converter;
import com.gmail.kurmazpavel.dao.PermissionDao;
import com.gmail.kurmazpavel.dao.RolesDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolesDao roleDao;
    @Autowired
    @Qualifier("permissionConverter")
    private Converter<PermissionDTO, Permission> permissionConverter;
    @Autowired
    @Qualifier("permissionDTOConverter")
    private DTOConverter<PermissionDTO, Permission> permissionDTOConverter;
    @Autowired
    @Qualifier("roleDTOConverter")
    private DTOConverter<RoleDTO, Role> roleDTOConverter;

    @Override
    public PermissionDTO read(Long entityId) {
        return permissionDTOConverter.toDTO(permissionDao.read(entityId));
    }

    @Override
    public PermissionDTO readByName(String name) {
        Session session = permissionDao.getCurrentSession();
        Query query = session.createQuery("from Permission as P where P.name =:name");
        query.setParameter("name", name);
        return permissionDTOConverter.toDTO((Permission) query.getSingleResult());

    }

    @Override
    public PermissionDTO deleteRole(Long entityID, Long roleId) {
        Permission permission = permissionDao.read(entityID);
        Role role = roleDao.read(roleId);
        permission.getRoles().remove(role);
        permissionDao.update(permission);
        return permissionDTOConverter.toDTO(permission);
    }

    @Override
    public PermissionDTO addRole(Long entityId, Long roleId) {
        Permission permission = permissionDao.read(entityId);
        Role role = roleDao.read(roleId);
        permission.getRoles().add(role);
        permissionDao.update(permission);
        return permissionDTOConverter.toDTO(permission);
    }

    @Override
    public List<PermissionDTO> getAll() {
        List<PermissionDTO> list = permissionDTOConverter.toDTOList(permissionDao.getAll());
        if (list != null)
            return list;
        else
            return new ArrayList<>();
    }

    @Override
    public List<RoleDTO> getRolesByName(String name) {
        Session session = permissionDao.getCurrentSession();
        Query query = session.createQuery("select P.roles from Permission as P where P.name =:name");
        query.setParameter("name", name);
        List<RoleDTO> roleList = roleDTOConverter.toDTOList(query.getResultList());
        if (roleList != null)
            return roleList;
        else
            return new ArrayList<>();
    }

    @Override
    public PermissionDTO create(PermissionDTO permissionDTO) {
        Permission permission = permissionConverter.toEntity(permissionDTO);
        permissionDao.create(permission);
        return permissionDTOConverter.toDTO(permission);
    }

    @Override
    public PermissionDTO update(PermissionDTO permissionDTO) {
        Permission permission = permissionDao.read(permissionDTO.getId());
        permissionDao.update(permission);
        return permissionDTOConverter.toDTO(permission);
    }

    @Override
    public PermissionDTO delete(PermissionDTO permissionDTO) {
        Permission permission = permissionConverter.toEntity(permissionDTO);
        permissionDao.delete(permission);
        return permissionDTOConverter.toDTO(permission);
    }
}
