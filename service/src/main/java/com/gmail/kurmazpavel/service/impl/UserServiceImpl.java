package com.gmail.kurmazpavel.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO read(Long entityId) {
        User user = dao.read(entityId);
        if (user == null)
            return null;
        return userDtoConverter.toDTO(user);
    }

    @Override
    public UserDTO create(UserDTO userDTO, String role) {
        Session session = dao.getCurrentSession();
        userDTO.setAddress(new AddressDTO());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Query query = session.createQuery("from Role as r where role.role = :role");
        query.setParameter("role", role);
        Role userRole = (Role) query.getSingleResult();
        User user = userConverter.toEntity(userDTO);
        user.setDisabled(false);
        userRole.getUsers().add(user);
        rolesDao.update(userRole);
        userDTO = userDtoConverter.toDTO(user);
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        dao.update(user);
        userDTO = userDtoConverter.toDTO(user);
        return userDTO;
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {
        User user = dao.read(userDTO.getId());
        dao.delete(user);
        userDTO = userDtoConverter.toDTO(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> users = userDtoConverter.toDTOList(dao.getAll());
        if (users != null)
            return users;
        else
            return new ArrayList<>();
    }

    @Override
    public UserDTO readByLogin(String login) {
        Session session = dao.getCurrentSession();
        Query query = session.createQuery("from User as u where u.login = :login");
        query.setParameter("login", login);
        Object object;
        try {
            object = query.getSingleResult();
        }
        catch (Exception e) {
            return null;
        }
        return userDtoConverter.toDTO((User) object);
    }

    @Override
    public UserDTO readByEmail(String email) {
        Session session = dao.getCurrentSession();
        Query query = session.createQuery("from User as U where U.email = :email");
        query.setParameter("email", email);
        return userDtoConverter.toDTO((User) query.getSingleResult());
    }
}