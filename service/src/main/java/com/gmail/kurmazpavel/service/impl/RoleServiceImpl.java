package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.service.RoleService;
import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.service.converter.Converter;
import com.gmail.kurmazpavel.dao.RolesDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    @Autowired
    private RolesDao rolesDao;
    @Autowired
    @Qualifier("roleConverter")
    private Converter<RoleDTO, Role> roleConverter;
    @Autowired
    @Qualifier("roleDTOConverter")
    private DTOConverter<RoleDTO, Role> roleDTOConverter;
    @Autowired
    @Qualifier("permissionDTOConverter")
    private DTOConverter<PermissionDTO, Permission> permissionDTOConverter;

    @Override
    public RoleDTO read(Long entityID) {
        Role role = rolesDao.read(entityID);
        return roleDTOConverter.toDTO(role);
    }

    @Override
    public List<RoleDTO> getAll() {
        List<RoleDTO> roles = roleDTOConverter.toDTOList(rolesDao.getAll());
        if (roles != null)
            return roles;
        else
            return new ArrayList<>();
    }

    @Override
    public List<PermissionDTO> getPermissions(Long id) {
        Role role = rolesDao.read(id);
        List<PermissionDTO> permissions = permissionDTOConverter.toDTOList(role.getPermissions());
        if (permissions != null)
            return permissions;
        else
            return new ArrayList<>();
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Role role = roleConverter.toEntity(roleDTO);
        rolesDao.create(role);
        return roleDTOConverter.toDTO(role);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        Role role = roleConverter.toEntity(roleDTO);
        rolesDao.update(role);
        return roleDTOConverter.toDTO(role);
    }

    @Override
    public RoleDTO delete(RoleDTO roleDTO) {
        Role role = roleConverter.toEntity(roleDTO);
        rolesDao.delete(role);
        return roleDTOConverter.toDTO(role);
    }

    @Override
    public RoleDTO readByRole(String roleName) {
        Session session = rolesDao.getCurrentSession();
        Query query = session.createQuery("from Role as R where R.role = :roleName");
        query.setParameter("roleName", roleName);
        return roleDTOConverter.toDTO((Role) query.getSingleResult());
    }
}
