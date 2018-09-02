package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.AdminDTOConverter;
import com.gmail.kurmazpavel.AdminService;
import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.converter.AdminConverter;
import com.gmail.kurmazpavel.genericDAO.GenericDAOImpl;
import com.gmail.kurmazpavel.genericDAO.AdminDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
    private GenericDAOImpl dao = new AdminDAOImpl(Admin.class);
    private AdminConverter converter = new AdminConverter();
    private AdminDTOConverter dtoConverter = new AdminDTOConverter();

    @Override
    public AdminDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Admin admin = (Admin) dao.read(entityID);
            transaction.commit();
            return dtoConverter.toDTO(admin);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read admin type!", e);
        }
        return null;
    }

    @Override
    public AdminDTO create(AdminDTO adminDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Admin admin = converter.toEntity(adminDTO);
            admin.setId(null);
            dao.create(admin);
            transaction.commit();
            return dtoConverter.toDTO(admin);
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
            transaction.commit();
            return dtoConverter.toDTO(admin);
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
            transaction.commit();
            return dtoConverter.toDTO(admin);
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
            List<Admin> list = dao.getAll();
            transaction.commit();
            return dtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list admins!", e);
        }
        return null;
    }
}
