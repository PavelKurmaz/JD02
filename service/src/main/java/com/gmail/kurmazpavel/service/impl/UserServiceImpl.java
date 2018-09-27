package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.Address;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.service.UserService;
import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.converter.Converter;
import com.gmail.kurmazpavel.dao.RolesDao;
import com.gmail.kurmazpavel.dao.UserDao;
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
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao dao;
    @Autowired
    private RolesDao rolesDao;
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDtoConverter;

    @Override
    public UserDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            User user = dao.read(entityID);
            UserDTO userDTO = userDtoConverter.toDTO(user);
            transaction.commit();
            return userDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read user type!", e);
        }
        return new UserDTO();
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            userDTO.setAddress(new AddressDTO());
            Query query = session.createQuery("from Role as r where role.role = :role");
            query.setParameter("role", "User");
            Role role = (Role) query.getSingleResult();
            User user = userConverter.toEntity(userDTO);
            user.setDisabled(false);
            role.getUsers().add(user);
            rolesDao.update(role);
            userDTO = userDtoConverter.toDTO(user);
            transaction.commit();
            return userDTO;
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
            userDTO = userDtoConverter.toDTO(user);
            transaction.commit();
            return userDTO;
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
            User user = dao.read(userDTO.getId());
            dao.delete(user);
            userDTO = userDtoConverter.toDTO(user);
            transaction.commit();
            return userDTO;
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
            List<UserDTO> users = userDtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return users;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list users!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public UserDTO readByLogin(String login) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from User as u where u.login = :login");
            query.setParameter("login", login);
            UserDTO userDTO = userDtoConverter.toDTO((User) query.getSingleResult());
            transaction.commit();
            return userDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read user type!", e);
        }
        return null;
    }

    @Override
    public UserDTO readByEmail(String email) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from User as U where U.email = :email");
            query.setParameter("email", email);
            UserDTO userDTO = userDtoConverter.toDTO((User) query.getSingleResult());
            transaction.commit();
            return userDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read user type!", e);
        }
        return null;
    }
}