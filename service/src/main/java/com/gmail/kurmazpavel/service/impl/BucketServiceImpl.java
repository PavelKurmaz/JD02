package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.BucketService;
import com.gmail.kurmazpavel.Bucket;
import com.gmail.kurmazpavel.dto.BucketDTO;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.dao.BucketDao;
import com.gmail.kurmazpavel.service.converter.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {
    private static final Logger logger = LogManager.getLogger(BucketServiceImpl.class);
    @Autowired
    private BucketDao dao;
    @Autowired
    @Qualifier("bucketConverter")
    private Converter<BucketDTO, Bucket> converter;
    @Autowired
    @Qualifier("bucketDTOConverter")
    private DTOConverter<BucketDTO, Bucket> dtoConverter;

    @Override
    public BucketDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            BucketDTO bucket = dtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return bucket;
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
            bucketDTO = dtoConverter.toDTO(bucket);
            transaction.commit();
            return bucketDTO;
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
            bucketDTO = dtoConverter.toDTO(bucket);
            transaction.commit();
            return bucketDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update bucket type!", e);
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
            bucketDTO = dtoConverter.toDTO(bucket);
            transaction.commit();
            return bucketDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete bucket type!", e);
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
            List<BucketDTO> list = dtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list buckets!", e);
        }
        return new ArrayList<>();
    }
}
