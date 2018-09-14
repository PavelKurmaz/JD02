package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.CatalogDTOConverter;
import com.gmail.kurmazpavel.DTOConverter.DiscountDTOConverter;
import com.gmail.kurmazpavel.DiscountService;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.Discount;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.DiscountDTO;
import com.gmail.kurmazpavel.converter.DiscountConverter;
import com.gmail.kurmazpavel.genericDAO.CatalogDao;
import com.gmail.kurmazpavel.genericDAO.DiscountDao;
import com.gmail.kurmazpavel.genericDAO.UserDao;
import com.gmail.kurmazpavel.genericDAO.impl.CatalogDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.DiscountDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);
    private DiscountDao dao = new DiscountDAOImpl(Discount.class);
    private CatalogDao catalogDao = new CatalogDAOImpl(Catalog.class);
    private DiscountConverter discountConverter = new DiscountConverter();
    private DiscountDTOConverter discountDtoConverter = new DiscountDTOConverter();
    private CatalogDTOConverter catalogDtoConverter = new CatalogDTOConverter();
    private UserDao userDao = new UserDAOImpl(User.class);

    @Override
    public DiscountDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Discount discount = dao.read(entityID);
            transaction.commit();
            return discountDtoConverter.toDTO(discount);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read discount type!", e);
        }
        return null;
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
            Discount discount = (Discount) query.getSingleResult();
            DiscountDTO discountDTO = discountDtoConverter.toDTO(discount);
            transaction.commit();
            return discountDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read discount type!", e);
        }
        return null;
    }

    @Override
    public DiscountDTO create(DiscountDTO discountDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Discount discount = discountConverter.toEntity(discountDTO);
            discount.setId(null);
            dao.create(discount);
            transaction.commit();
            return discountDtoConverter.toDTO(discount);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create discount type!", e);
        }
        return discountDTO;
    }

    @Override
    public void update(long discountId, long userId) {
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
            transaction.commit();
            return discountDtoConverter.toDTO(discount);
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
            List<Discount> list = dao.getAll();
            transaction.commit();
            return discountDtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list discounts!", e);
        }
        return null;
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
                List<Catalog> itemsList = query.getResultList();
                List<CatalogDTO> items = catalogDtoConverter.toDTOList(itemsList);
                transaction.commit();
                return items;
            }
            else {
                Query query = session.createQuery("from Catalog");
                List<Catalog> all = query.getResultList();
                List<Catalog> items = new ArrayList<>();
                for (Catalog item: all) {
                    if (item.getDiscounts().isEmpty())
                            items.add(item);
                }
                List<CatalogDTO> result = catalogDtoConverter.toDTOList(items);
                transaction.commit();
                return result;
            }
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list items!", e);
        }
        return null;
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