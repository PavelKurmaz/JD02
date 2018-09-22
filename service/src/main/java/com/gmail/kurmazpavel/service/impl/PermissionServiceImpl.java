package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.DTOConverter.PermissionDTOConverter;
import com.gmail.kurmazpavel.DTOConverter.RoleDTOConverter;
import com.gmail.kurmazpavel.service.PermissionService;
import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.converter.PermissionConverter;
import com.gmail.kurmazpavel.dao.PermissionDao;
import com.gmail.kurmazpavel.dao.RolesDao;
import com.gmail.kurmazpavel.dao.impl.PermissionDAOImpl;
import com.gmail.kurmazpavel.dao.impl.RoleDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);
    private PermissionDao permissionDao = new PermissionDAOImpl();
    private RolesDao roleDao = new RoleDAOImpl();
    private PermissionConverter permissionConverter = new PermissionConverter();
    private PermissionDTOConverter permissionDTOConverter = new PermissionDTOConverter();
    private RoleDTOConverter roleDTOConverter = new RoleDTOConverter();

    @Override
    public PermissionDTO read(Long entityId) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            PermissionDTO permission = permissionDTOConverter.toDTO(permissionDao.read(entityId));
            transaction.commit();
            return permission;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return new PermissionDTO();
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
            PermissionDTO permission = permissionDTOConverter.toDTO((Permission) query.getSingleResult());
            transaction.commit();
            return permission;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return new PermissionDTO();
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
            PermissionDTO permissionDTO = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            return permissionDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete permission type!", e);
        }
        return new PermissionDTO();
    }

    @Override
    public PermissionDTO addRole(Long entityId, Long roleId) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionDao.read(entityId);
            Role role = roleDao.read(roleId);
            permission.getRoles().add(role);
            permissionDao.update(permission);
            PermissionDTO permissionDTO = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            return permissionDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read permission type!", e);
        }
        return new PermissionDTO();
    }

    @Override
    public List<PermissionDTO> getAll() {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<PermissionDTO> list = permissionDTOConverter.toDTOList(permissionDao.getAll());
            transaction.commit();
            return list;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list permissions!", e);
        }
        return new ArrayList<>();
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
            List<RoleDTO> roleList = roleDTOConverter.toDTOList(query.getResultList());
            transaction.commit();
            return roleList;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read roles by permission!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public PermissionDTO create(PermissionDTO permissionDTO) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Permission permission = permissionConverter.toEntity(permissionDTO);
            permissionDao.create(permission);
            permissionDTO = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            return permissionDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create permission type!", e);
        }
        return new PermissionDTO();
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
            permissionDTO = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            return permissionDTO;
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
            permissionDTO = permissionDTOConverter.toDTO(permission);
            transaction.commit();
            return permissionDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete user type!", e);
        }
        return permissionDTO;
    }
}
