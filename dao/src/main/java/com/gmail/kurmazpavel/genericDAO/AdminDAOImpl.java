package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminDAOImpl extends GenericDAOImpl<Admin>{
    private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class);
    public AdminDAOImpl(Class<Admin> adminClass) {
        super(adminClass);
    }
}
