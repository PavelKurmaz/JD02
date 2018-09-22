package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Role;
import com.gmail.kurmazpavel.dao.RolesDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RolesDao {
    private static final Logger logger = LogManager.getLogger(RoleDAOImpl.class);
    public RoleDAOImpl() {
        super(Role.class);
    }
}
