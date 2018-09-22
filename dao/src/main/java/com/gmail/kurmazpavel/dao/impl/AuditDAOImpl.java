package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Audit;
import com.gmail.kurmazpavel.dao.AuditDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDAOImpl extends GenericDAOImpl<Audit> implements AuditDao{
    private static final Logger logger = LogManager.getLogger(AuditDAOImpl.class);
    public AuditDAOImpl() {
        super(Audit.class);
    }
}
