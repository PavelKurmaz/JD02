package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.BucketDTOConverter;
import com.gmail.kurmazpavel.BucketService;
import com.gmail.kurmazpavel.beans.Bucket;
import com.gmail.kurmazpavel.beans.dto.BucketDTO;
import com.gmail.kurmazpavel.converter.BucketConverter;
import com.gmail.kurmazpavel.genericDAO.BucketDao;
import com.gmail.kurmazpavel.genericDAO.impl.BucketDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BucketServiceImpl implements BucketService {

    private static final Logger logger = LogManager.getLogger(BucketServiceImpl.class);
    private BucketDao dao = new BucketDAOImpl(Bucket.class);
    private BucketConverter converter = new BucketConverter();
    private BucketDTOConverter dtoConverter = new BucketDTOConverter();

    @Override
    public BucketDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Bucket bucket = dao.read(entityID);
            transaction.commit();
            return dtoConverter.toDTO(bucket);
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read bucket type!", e);
        }
        return null;
    }

    @Override
    public BucketDTO create(BucketDTO bucketDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Bucket bucket = converter.toEntity(bucketDTO);
            dao.create(bucket);
            transaction.commit();
            return dtoConverter.toDTO(bucket);
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create bucket type!", e);
        }
        return bucketDTO;
    }

    @Override
    public BucketDTO update(BucketDTO bucketDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Bucket bucket = dao.read(bucketDTO.getId());
            bucket.setStatus(bucketDTO.getStatus());
            dao.update(bucket);
            transaction.commit();
            return dtoConverter.toDTO(bucket);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update news type!", e);
        }
        return bucketDTO;
    }

    @Override
    public BucketDTO delete(BucketDTO bucketDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Bucket bucket = dao.read(bucketDTO.getId());
            dao.delete(bucket);
            transaction.commit();
            return dtoConverter.toDTO(bucket);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update bucket type!", e);
        }
        return bucketDTO;
    }

    @Override
    public List<BucketDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<Bucket> list = dao.getAll();
            transaction.commit();
            return dtoConverter.toDTOList(list);
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list buckets!", e);
        }
        return null;
    }
}
