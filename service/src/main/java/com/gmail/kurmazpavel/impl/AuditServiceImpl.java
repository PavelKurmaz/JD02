package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.AuditService;
import com.gmail.kurmazpavel.DTOConverter.AuditDTOConverter;
import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.converter.AuditConverter;
import com.gmail.kurmazpavel.genericDAO.AuditDao;
import com.gmail.kurmazpavel.genericDAO.UserDao;
import com.gmail.kurmazpavel.genericDAO.impl.AuditDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AuditServiceImpl implements AuditService {
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    private AuditDao dao = new AuditDAOImpl(Audit.class);
    private UserDao userDao = new UserDAOImpl(User.class);
    private AuditConverter converter = new AuditConverter();
    private AuditDTOConverter dtoconverter = new AuditDTOConverter();

    public AuditDTO create(AuditDTO auditDTO){
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Audit audit = converter.toEntity(auditDTO);
            dao.save(audit);
            auditDTO = dtoconverter.toDTO(audit);
            transaction.commit();
            return auditDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create audit type!", e);
        }
        return auditDTO;
    }

    public void update (AuditDTO auditDTO){
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Audit audit = converter.toEntity(auditDTO);
            dao.update(audit);
            transaction.commit();
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create audit type!", e);
        }
    }

    public List<AuditDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<AuditDTO> audits = dtoconverter.toDTOList(dao.getAll());
            transaction.commit();
            return audits;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list audit!", e);
        }
        return new ArrayList<>();
    }
}
