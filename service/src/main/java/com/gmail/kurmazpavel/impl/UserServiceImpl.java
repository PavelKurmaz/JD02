package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.UserDTOConverter;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.converter.UserConverter;
import com.gmail.kurmazpavel.genericDAO.RolesDao;
import com.gmail.kurmazpavel.genericDAO.UserDao;
import com.gmail.kurmazpavel.genericDAO.impl.RoleDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao dao = new UserDAOImpl(User.class);
    private RolesDao rolesDao = new RoleDAOImpl(Role.class);
    private UserConverter userConverter = new UserConverter();
    private UserDTOConverter userDtoConverter = new UserDTOConverter();

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
            Query query = session.createQuery("from Role as r where role.role = :role");
            query.setParameter("role", "User");
            Role role = (Role) query.getSingleResult();
            User user = userConverter.toEntity(userDTO);
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