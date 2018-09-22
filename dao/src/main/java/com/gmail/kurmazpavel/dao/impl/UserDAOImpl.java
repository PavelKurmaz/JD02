package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    public UserDAOImpl() {
        super(User.class);
    }
}
