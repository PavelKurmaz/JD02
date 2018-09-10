package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.UserDTOConverter;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.beans.Permission;
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
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao dao = new UserDAOImpl(User.class);
    private UserConverter userConverter = new UserConverter();
    private RolesDao rolesDao = new RoleDAOImpl(Role.class);
    private UserDTOConverter userDtoConverter = new UserDTOConverter();

    @Override
    public UserDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            User user = dao.read(entityID);
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
            Address address = new Address();
            User user = userConverter.toEntity(userDTO);
            user.setId(null);
            user.setAddress(address);
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
            User user = dao.read(userDTO.getId());
            user.setPhone(userDTO.getPhone());
            user.setPassword(userDTO.getPassword());
            user.setLogin(userDTO.getLogin());
            user.setEmail(userDTO.getEmail());
            user.setCarma(userDTO.getCarma());
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

    @Override
    public UserDTO readByLogin(String login) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from User as U where U.login = :login");
            query.setParameter("login", login);
            User user = (User) query.getSingleResult();
            transaction.commit();
            if (user != null)
                return userDtoConverter.toDTO(user);
            return null;
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
            User user = (User) query.getSingleResult();
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
    public UserDTO checkPermission(UserDTO userDTO, String permissionName) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Role role = rolesDao.read(userDTO.getRoles_id());
            transaction.commit();
            List<Permission> permissions = role.getPermissions();
            for (Permission permission: permissions) {
                if (permission.getName().equals(permissionName))
                    return userDTO;
            }
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