package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.genericDAO.RolesDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleDAOImpl extends GenericDAOImpl<Role> implements RolesDao {
    private static final Logger logger = LogManager.getLogger(RoleDAOImpl.class);
    public RoleDAOImpl(Class<Role> roleClass) {
        super(roleClass);
    }
}
