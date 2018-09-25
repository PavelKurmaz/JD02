package com.gmail.kurmazpavel.service.impl;

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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
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
    private DTOConverter<PermissionDTO, com.gmail.kurmazpavel.Permission> permissionDTOConverter;

    @Override
    public RoleDTO read(Long entityID) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            RoleDTO role = roleDTOConverter.toDTO(rolesDao.read(entityID));
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read role type!", e);
        }
        return new RoleDTO();
    }

    @Override
    public List<RoleDTO> getAll() {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<RoleDTO> roles = roleDTOConverter.toDTOList(rolesDao.getAll());
            transaction.commit();
            return roles;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list roles!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<PermissionDTO> getPermissions(Long id) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = rolesDao.read(id);
            List<PermissionDTO> permissions = permissionDTOConverter.toDTOList(role.getPermissions());
            transaction.commit();
            return permissions;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list permissions!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = roleConverter.toEntity(roleDTO);
            rolesDao.create(role);
            roleDTO = roleDTOConverter.toDTO(role);
            transaction.commit();
            return roleDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create role type!", e);
        }
        return roleDTO;
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = roleConverter.toEntity(roleDTO);
            rolesDao.update(role);
            roleDTO = roleDTOConverter.toDTO(role);
            transaction.commit();
            return roleDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update role type!", e);
        }
        return roleDTO;
    }

    @Override
    public RoleDTO delete(RoleDTO roleDTO) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = roleConverter.toEntity(roleDTO);
            rolesDao.delete(role);
            roleDTO = roleDTOConverter.toDTO(role);
            transaction.commit();
            return roleDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete role type!", e);
        }
        return roleDTO;
    }

    @Override
    public RoleDTO readByRole(String roleName) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from Role as R where R.role = :roleName");
            query.setParameter("roleName", roleName);
            RoleDTO role = roleDTOConverter.toDTO((Role) query.getSingleResult());
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read role type!", e);
        }
        return new RoleDTO();
    }
}
