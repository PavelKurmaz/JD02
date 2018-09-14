package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.DTOConverter.CatalogDTOConverter;
import com.gmail.kurmazpavel.DTOConverter.DiscountDTOConverter;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.Discount;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.DiscountDTO;
import com.gmail.kurmazpavel.converter.CatalogConverter;
import com.gmail.kurmazpavel.genericDAO.CatalogDao;
import com.gmail.kurmazpavel.genericDAO.impl.CatalogDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class CatalogServiceImpl implements CatalogService {
    private static final Logger logger = LogManager.getLogger(CatalogServiceImpl.class);
    private CatalogDao dao = new CatalogDAOImpl(Catalog.class);
    private CatalogConverter converter = new CatalogConverter();
    private CatalogDTOConverter dtoConverter = new CatalogDTOConverter();
    private DiscountDTOConverter discountDtoConverter = new DiscountDTOConverter();

    @Override
    public CatalogDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog catalog = dao.read(entityID);
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
            catalog.setId(null);
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
            Catalog catalog = dao.read(catalogDTO.getID());
            catalog.setAmount(catalogDTO.getAmount());
            catalog.setPrice(catalogDTO.getPrice());
            catalog.setName(catalogDTO.getName());
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
            Catalog catalog = dao.read(catalogDTO.getID());
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

    @Override
    public Long count(Double min, Double max) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("select count(*) from Catalog as c where c.price <= :max AND c.price >= :min");
            query.setParameter("min", min);
            query.setParameter("max", max);
            return (Long) query.getSingleResult();
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list catalogs!", e);
        }
        return null;
    }

    @Override
    public List<CatalogDTO> getByPrice(Double min, Double max) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from Catalog as c where c.price <= :max AND c.price >= :min");
            query.setParameter("min", min);
            query.setParameter("max", max);
            List<Catalog> list = query.getResultList();
            List<CatalogDTO> finalList = dtoConverter.toDTOList(list);
            transaction.commit();
            return finalList;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list catalogs!", e);
        }
        return null;
    }

    @Override
    public List<DiscountDTO> getDiscounts(Long id) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("select c.discounts from Catalog as c where c.id=:id");
            query.setParameter("id", id);
            List<Discount> discounts = query.getResultList();
            transaction.commit();
            return discountDtoConverter.toDTOList(discounts);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list catalogs!", e);
        }
        return null;
    }

}
