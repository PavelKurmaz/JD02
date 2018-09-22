package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Permission;
import com.gmail.kurmazpavel.dao.PermissionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDAOImpl extends GenericDAOImpl<Permission> implements PermissionDao {
    private static final Logger logger = LogManager.getLogger(PermissionDAOImpl.class);
    public PermissionDAOImpl() {
        super(Permission.class);
    }
}
