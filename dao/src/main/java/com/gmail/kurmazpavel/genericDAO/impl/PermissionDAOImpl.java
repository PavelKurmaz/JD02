package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Permission;
import com.gmail.kurmazpavel.genericDAO.PermissionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PermissionDAOImpl extends GenericDAOImpl<Permission> implements PermissionDao {
    private static final Logger logger = LogManager.getLogger(PermissionDAOImpl.class);
    public PermissionDAOImpl(Class<Permission> aClass) {
        super(aClass);
    }
}
