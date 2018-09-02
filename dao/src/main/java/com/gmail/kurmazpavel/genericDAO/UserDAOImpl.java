package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAOImpl extends GenericDAOImpl<User>{
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    public UserDAOImpl(Class<User> userClass) {
        super(userClass);
    }
}
