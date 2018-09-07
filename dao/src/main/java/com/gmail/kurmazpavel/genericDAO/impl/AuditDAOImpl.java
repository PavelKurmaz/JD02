package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.genericDAO.AuditDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditDAOImpl extends GenericDAOImpl<Audit> implements AuditDao{
    private static final Logger logger = LogManager.getLogger(AuditDAOImpl.class);
    public AuditDAOImpl(Class<Audit> auditClass) {
        super(auditClass);
    }
}
