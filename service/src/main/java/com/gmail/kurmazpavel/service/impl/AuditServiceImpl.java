package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.AuditService;
import com.gmail.kurmazpavel.DTOConverter.AuditDTOConverter;
import com.gmail.kurmazpavel.Audit;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.AuditDTO;
import com.gmail.kurmazpavel.converter.AuditConverter;
import com.gmail.kurmazpavel.dao.AuditDao;
import com.gmail.kurmazpavel.dao.UserDao;
import com.gmail.kurmazpavel.dao.impl.AuditDAOImpl;
import com.gmail.kurmazpavel.dao.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AuditServiceImpl implements AuditService {
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    private AuditDao dao = new AuditDAOImpl();
    private UserDao userDao = new UserDAOImpl();
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
