package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.AdminService;
import com.gmail.kurmazpavel.Admin;
import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.dao.AdminDao;
import com.gmail.kurmazpavel.dao.RolesDao;
import com.gmail.kurmazpavel.service.converter.Converter;
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
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
    @Autowired
    private AdminDao dao;
    @Autowired
    private RolesDao rolesDao;
    @Autowired
    @Qualifier("adminConverter")
    private Converter<AdminDTO, Admin> converter;
    @Autowired
    @Qualifier("adminDTOConverter")
    private DTOConverter<AdminDTO, Admin> dtoConverter;

    @Override
    public AdminDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            AdminDTO adminDTO = dtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return adminDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read admin type!", e);
        }
        return null;
    }

    @Override
    public AdminDTO readByLogin(String login) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from Admin as A where A.login = :login");
            query.setParameter("login", login);
            AdminDTO admin = dtoConverter.toDTO((Admin) query.getSingleResult());
            transaction.commit();
            return admin;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read admin type!", e);
        }
        return new AdminDTO();
    }

    @Override
    public AdminDTO create(AdminDTO adminDTO, String role) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Admin admin = converter.toEntity(adminDTO);
            Query query = session.createQuery("from Role as r  where r.role=:role");
            query.setParameter("role", role);
            Role admRole = (Role) query.getSingleResult();
            admRole.getAdmins().add(admin);
            rolesDao.update(admRole);
            adminDTO = dtoConverter.toDTO(admin);
            transaction.commit();
            return adminDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create admin type!", e);
        }
        return adminDTO;
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Admin admin = converter.toEntity(adminDTO);
            dao.update(admin);
            adminDTO = dtoConverter.toDTO(admin);
            transaction.commit();
            return adminDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update admin type!", e);
        }
        return adminDTO;
    }

    @Override
    public AdminDTO delete(AdminDTO adminDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Admin admin = converter.toEntity(adminDTO);
            dao.delete(admin);
            adminDTO = dtoConverter.toDTO(admin);
            transaction.commit();
            return adminDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete admin type!", e);
        }
        return adminDTO;
    }

    @Override
    public List<AdminDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<AdminDTO> admins = dtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return admins;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list admins!", e);
        }
        return new ArrayList<>();
    }
}
