package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.DTOConverter.CatalogDTOConverter;
import com.gmail.kurmazpavel.DTOConverter.DiscountDTOConverter;
import com.gmail.kurmazpavel.service.DiscountService;
import com.gmail.kurmazpavel.Catalog;
import com.gmail.kurmazpavel.Discount;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.CatalogDTO;
import com.gmail.kurmazpavel.dto.DiscountDTO;
import com.gmail.kurmazpavel.converter.DiscountConverter;
import com.gmail.kurmazpavel.dao.CatalogDao;
import com.gmail.kurmazpavel.dao.DiscountDao;
import com.gmail.kurmazpavel.dao.UserDao;
import com.gmail.kurmazpavel.dao.impl.CatalogDAOImpl;
import com.gmail.kurmazpavel.dao.impl.DiscountDAOImpl;
import com.gmail.kurmazpavel.dao.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);
    private DiscountDao dao = new DiscountDAOImpl();
    private CatalogDao catalogDao = new CatalogDAOImpl();
    private DiscountConverter discountConverter = new DiscountConverter();
    private DiscountDTOConverter discountDtoConverter = new DiscountDTOConverter();
    private CatalogDTOConverter catalogDtoConverter = new CatalogDTOConverter();
    private UserDao userDao = new UserDAOImpl();

    @Override
    public DiscountDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            DiscountDTO discount = discountDtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return discount;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read discount type!", e);
        }
        return new DiscountDTO();
    }

    @Override
    public DiscountDTO readByName(String name) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("from Discount as d where d.name=:name");
            query.setParameter("name", name);
            DiscountDTO discount = discountDtoConverter.toDTO((Discount) query.getSingleResult());
            transaction.commit();
            return discount;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read discount type!", e);
        }
        return new DiscountDTO();
    }

    @Override
    public DiscountDTO create(DiscountDTO discountDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Discount discount = discountConverter.toEntity(discountDTO);
            dao.create(discount);
            discountDTO = discountDtoConverter.toDTO(discount);
            transaction.commit();
            return discountDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create discount type!", e);
        }
        return discountDTO;
    }

    @Override
    public void update(Long discountId, Long userId) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Discount discount = dao.read(discountId);
            User user = userDao.read(userId);
            discount.getUsers().add(user);
            user.setDiscount(discount);
            dao.update(discount);
            transaction.commit();
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update discount type!", e);
        }
    }

    @Override
    public DiscountDTO delete(DiscountDTO discountDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Discount discount = discountConverter.toEntity(discountDTO);
            dao.delete(discount);
            discountDTO = discountDtoConverter.toDTO(discount);
            transaction.commit();
            return discountDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete discount type!", e);
        }
        return discountDTO;
    }

    @Override
    public List<DiscountDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<DiscountDTO> list = discountDtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return list;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list discounts!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<CatalogDTO> getByDiscount(Integer discount) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            if (discount != 0) {
                Query query = session.createQuery("select d.discountItems from Discount as d where d.percent = :percent");
                query.setParameter("percent", discount);
                List<CatalogDTO> items = discountDtoConverter.toDTOList(query.getResultList());
                transaction.commit();
                return items;
            }
            else {
                Query query = session.createQuery("from Catalog as c where c.discounts is empty");
                List<CatalogDTO> items = catalogDtoConverter.toDTOList(query.getResultList());
                transaction.commit();
                return items;
            }
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list items!", e);
        }
        return new ArrayList<>();
    }


    public void applyDiscount(Long itemId, Long discountId) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog catalog = catalogDao.read(itemId);
            Discount discount = dao.read(discountId);
            discount.getDiscountItems().add(catalog);
            dao.update(discount);
            transaction.commit();
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to apply discounts!", e);
        }
    }
}