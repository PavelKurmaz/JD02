package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.Audit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditDAOImpl extends GenericDAOImpl<Audit>{
    private static final Logger logger = LogManager.getLogger(AuditDAOImpl.class);
    public AuditDAOImpl(Class<Audit> auditClass) {
        super(auditClass);
    }
}
