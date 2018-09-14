package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.PermissionDTOConverter;
import com.gmail.kurmazpavel.DTOConverter.RoleDTOConverter;
import com.gmail.kurmazpavel.RoleService;
import com.gmail.kurmazpavel.beans.Permission;
import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.beans.dto.PermissionDTO;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.converter.RoleConverter;
import com.gmail.kurmazpavel.genericDAO.RolesDao;
import com.gmail.kurmazpavel.genericDAO.impl.RoleDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    private RolesDao rolesDao = new RoleDAOImpl(Role.class);
    private RoleConverter roleConverter = new RoleConverter();
    private RoleDTOConverter roleDTOConverter = new RoleDTOConverter();
    private PermissionDTOConverter permissionDTOConverter = new PermissionDTOConverter();

    @Override
    public RoleDTO read(Long entityID) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = rolesDao.read(entityID);
            transaction.commit();
            return roleDTOConverter.toDTO(role);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read role type!", e);
        }
        return null;
    }

    @Override
    public List<RoleDTO> getAll() {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<Role> list = rolesDao.getAll();
            transaction.commit();
            return roleDTOConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list roles!", e);
        }
        return null;
    }

    @Override
    public List<PermissionDTO> getPermissions(Long id) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = rolesDao.read(id);
            List<Permission> permissions = role.getPermissions();
            transaction.commit();
            return permissionDTOConverter.toDTOList(permissions);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list roles!", e);
        }
        return null;
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Session session = rolesDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = roleConverter.toEntity(roleDTO);
            role.setId(null);
            rolesDao.create(role);
            roleDTO = roleDTOConverter.toDTO(role);
            transaction.commit();
            return roleDTO;
        }
        catch (Exception e) {
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
            Role role = rolesDao.read(roleDTO.getId());
            rolesDao.update(role);
            transaction.commit();
            return roleDTOConverter.toDTO(role);
        }
        catch (Exception e) {
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
            transaction.commit();
            return roleDTOConverter.toDTO(role);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete user type!", e);
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
            Role role = (Role) query.getSingleResult();
            transaction.commit();
            if (role != null)
                return roleDTOConverter.toDTO(role);
            return null;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read user type!", e);
        }
        return null;
    }
}
