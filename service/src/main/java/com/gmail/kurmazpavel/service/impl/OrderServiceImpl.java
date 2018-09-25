package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.Bucket;
import com.gmail.kurmazpavel.Catalog;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dao.BucketDao;
import com.gmail.kurmazpavel.dao.CatalogDao;
import com.gmail.kurmazpavel.dao.OrderDao;
import com.gmail.kurmazpavel.dao.UserDao;
import com.gmail.kurmazpavel.service.OrderService;
import com.gmail.kurmazpavel.dto.OrderDTO;
import com.gmail.kurmazpavel.service.converter.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao dao;
    @Autowired
    private CatalogDao itemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BucketDao bucketDao;
    @Autowired
    @Qualifier("orderConverter")
    private Converter<OrderDTO, Order> converter;
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<OrderDTO, Order> dtoConverter;

    @Override
    public OrderDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            OrderDTO orderDTO= dtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return orderDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read order type!", e);
        }
        return new OrderDTO();
    }

    @Override
    public void create(OrderDTO orderDTO, Long bucketId) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Order order = converter.toEntity(orderDTO);
            Catalog item = itemDao.read(orderDTO.getItemId());
            User user = userDao.read(orderDTO.getUserId());
            Bucket bucket = bucketDao.read(bucketId);
            bucket.getOrders().add(order);
            bucketDao.update(bucket);
            user.getOrders().add(order);
            item.getOrders().add(order);
            itemDao.update(item);
            userDao.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create order type!", e);
        }
    }

    @Override
    public List<OrderDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<OrderDTO> list = dtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list orders!", e);
        }
        return new ArrayList<>();
    }

    public List<OrderDTO> getById(Long userId) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("select U.orders from User as U where U.id = :userId");
            query.setParameter("userId", userId);
            List<OrderDTO> list = dtoConverter.toDTOList(query.getResultList());
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list orders!", e);
        }
        return new ArrayList<>();
    }
}
