package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.genericDAO.AdminDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminDAOImpl extends GenericDAOImpl<Admin> implements AdminDao{
    private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class);
    public AdminDAOImpl(Class<Admin> adminClass) {
        super(adminClass);
    }
}
