package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.UserDTOConverter;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.converter.UserConverter;
import com.gmail.kurmazpavel.genericDAO.GenericDAOImpl;
import com.gmail.kurmazpavel.genericDAO.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private GenericDAOImpl dao = new UserDAOImpl(User.class);
    private UserConverter userConverter = new UserConverter();
    private UserDTOConverter userDtoConverter = new UserDTOConverter();

    @Override
    public UserDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            User user = (User) dao.read(entityID);
            transaction.commit();
            return userDtoConverter.toDTO(user);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read user type!", e);
        }
        return null;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Audit audit = new Audit();
            audit.setEvent_type("User register");
            audit.setCreated(LocalDateTime.now());
            Address address = new Address();
            User user = userConverter.toEntity(userDTO);
            user.setId(null);
            user.setAudit(audit);
            user.setAddress(address);
            address.setUser(user);
            audit.setUser(user);
            dao.create(user);
            transaction.commit();
            return userDtoConverter.toDTO(user);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create user type!", e);
        }
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            User user = userConverter.toEntity(userDTO);
            dao.update(user);
            transaction.commit();
            return userDtoConverter.toDTO(user);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update user type!", e);
        }
        return userDTO;
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            User user = userConverter.toEntity(userDTO);
            dao.delete(user);
            transaction.commit();
            return userDtoConverter.toDTO(user);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete user type!", e);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<User> list = dao.getAll();
            transaction.commit();
            return userDtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list users!", e);
        }
        return null;
    }
}