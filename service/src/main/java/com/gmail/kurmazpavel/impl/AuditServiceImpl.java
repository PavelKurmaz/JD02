package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.AuditDTOConverter;
import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.converter.AuditConverter;
import com.gmail.kurmazpavel.genericDAO.AuditDAOImpl;
import com.gmail.kurmazpavel.genericDAO.GenericDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuditServiceImpl implements AuditService{
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    private GenericDAOImpl dao = new AuditDAOImpl(Audit.class);
    private AuditConverter converter = new AuditConverter();
    private AuditDTOConverter dtoconverter = new AuditDTOConverter();

    public AuditDTO create(AuditDTO auditDTO){
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Audit audit = converter.toEntity(auditDTO);
            dao.create(audit);
            transaction.commit();
            return dtoconverter.toDTO(audit);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create user!", e);
        }
        return auditDTO;
    }
    public List<AuditDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<Audit> list = dao.getAll();
            transaction.commit();
            return dtoconverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list audit!", e);
        }
        return null;
    }
}
