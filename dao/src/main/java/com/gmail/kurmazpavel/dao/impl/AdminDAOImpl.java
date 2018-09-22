package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Admin;
import com.gmail.kurmazpavel.dao.AdminDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl extends GenericDAOImpl<Admin> implements AdminDao{
    private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class);
    public AdminDAOImpl() {
        super(Admin.class);
    }
}
