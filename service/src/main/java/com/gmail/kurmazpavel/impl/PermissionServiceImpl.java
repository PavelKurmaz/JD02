package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.PermissionDTOConverter;
import com.gmail.kurmazpavel.DTOConverter.RoleDTOConverter;
import com.gmail.kurmazpavel.PermissionService;
import com.gmail.kurmazpavel.beans.Permission;
import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.beans.dto.PermissionDTO;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.converter.PermissionConverter;
import com.gmail.kurmazpavel.genericDAO.PermissionDao;
import com.gmail.kurmazpavel.genericDAO.RolesDao;
import com.gmail.kurmazpavel.genericDAO.impl.PermissionDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.RoleDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);
    private PermissionDao permissionDao = new PermissionDAOImpl(Permission.class);
    private RolesDao roleDao = new RoleDAOImpl(Role.class);
    private PermissionConverter permissionConverter = new PermissionConverter();
    private PermissionDTOConverter permissionDTOConverter = new PermissionDTOConverter();
    private RoleDTOConverter roleDTOConverter = new RoleDTOConverter();

    @Override
    public PermissionDTO read(Long entityID) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionDao.read(entityID);
            transaction.commit();
            return permissionDTOConverter.toDTO(permission);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return null;
    }

    @Override
    public PermissionDTO readByName(String name) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from Permission as P where P.name =:name");
            query.setParameter("name", name);
            Permission permission = (Permission) query.getSingleResult();
            transaction.commit();
            return permissionDTOConverter.toDTO(permission);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return null;
    }

    @Override
    public PermissionDTO deleteRole(Long entityID, Long roleId) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionDao.read(entityID);
            Role role = roleDao.read(roleId);
            permission.getRoles().remove(role);
            permissionDao.update(permission);
            transaction.commit();
            return permissionDTOConverter.toDTO(permission);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return null;
    }

    @Override
    public PermissionDTO addRole(Long entityID, Long roleId) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionDao.read(entityID);
            Role role = roleDao.read(roleId);
            permission.getRoles().add(role);
            permissionDao.update(permission);
            transaction.commit();
            return permissionDTOConverter.toDTO(permission);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return null;
    }

    @Override
    public List<PermissionDTO> getAll() {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<Permission> list = permissionDao.getAll();
            transaction.commit();
            return permissionDTOConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list permissions!", e);
        }
        return null;
    }

    @Override
    public List<RoleDTO> getRolesByName(String name) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("select P.roles from Permission as P where P.name =:name");
            query.setParameter("name", name);
            List<Role> roleList =  query.getResultList();
            transaction.commit();
            return roleDTOConverter.toDTOList(roleList);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return null;
    }

    @Override
    public void create(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionConverter.toEntity(permissionDTO);
            permission.setId(null);
            permissionDao.create(permission);
            transaction.commit();
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create permission type!", e);
        }
    }

    @Override
    public PermissionDTO update(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionDao.read(permissionDTO.getId());
            permissionDao.update(permission);
            transaction.commit();
            return permissionDTOConverter.toDTO(permission);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update permission type!", e);
        }
        return permissionDTO;
    }

    @Override
    public PermissionDTO delete(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionConverter.toEntity(permissionDTO);
            permissionDao.delete(permission);
            transaction.commit();
            return permissionDTOConverter.toDTO(permission);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete user type!", e);
        }
        return permissionDTO;
    }
}