package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.DTOConverter.CatalogDTOConverter;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.converter.CatalogConverter;
import com.gmail.kurmazpavel.genericDAO.impl.CatalogDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.GenericDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatalogServiceImpl implements CatalogService {
    private static final Logger logger = LogManager.getLogger(CatalogServiceImpl.class);
    private GenericDAOImpl dao = new CatalogDAOImpl(Catalog.class);
    private CatalogConverter converter = new CatalogConverter();
    private CatalogDTOConverter dtoConverter = new CatalogDTOConverter();

    @Override
    public CatalogDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog catalog = (Catalog) dao.read(entityID);
            transaction.commit();
            return dtoConverter.toDTO(catalog);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read catalog type!", e);
        }
        return null;
    }

    @Override
    public CatalogDTO create(CatalogDTO catalogDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog catalog = converter.toEntity(catalogDTO);
            dao.create(catalog);
            transaction.commit();
            return dtoConverter.toDTO(catalog);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create catalog type!", e);
        }
        return catalogDTO;
    }

    @Override
    public CatalogDTO update(CatalogDTO catalogDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog catalog = (Catalog) dao.read(catalogDTO.getID());
            catalog.setAmount(catalogDTO.getAmount());
            dao.update(catalog);
            transaction.commit();
            return dtoConverter.toDTO(catalog);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update catalog type!", e);
        }
        return catalogDTO;
    }

    @Override
    public CatalogDTO delete(CatalogDTO catalogDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog catalog = (Catalog) dao.read(catalogDTO.getID());
            dao.delete(catalog);
            transaction.commit();
            return dtoConverter.toDTO(catalog);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete catalog type!", e);
        }
        return catalogDTO;
    }

    @Override
    public List<CatalogDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<Catalog> list = dao.getAll();
            transaction.commit();
            return dtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list catalogs!", e);
        }
        return null;
    }
}
