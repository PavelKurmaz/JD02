package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.genericDAO.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    public UserDAOImpl(Class<User> userClass) {
        super(userClass);
    }
}
