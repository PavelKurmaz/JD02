package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.OrderDTOConverter;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.*;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.converter.OrderConverter;
import com.gmail.kurmazpavel.genericDAO.*;
import com.gmail.kurmazpavel.genericDAO.impl.BucketDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.CatalogDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.OrderDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private OrderDao dao = new OrderDAOImpl(Order.class);
    private CatalogDao itemDao = new CatalogDAOImpl(Catalog.class);
    private UserDao userDao = new UserDAOImpl(User.class);
    private BucketDao bucketDao = new BucketDAOImpl(Bucket.class);
    private OrderConverter converter = new OrderConverter();
    private OrderDTOConverter dtoConverter = new OrderDTOConverter();

    @Override
    public OrderDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Order order = dao.read(entityID);
            transaction.commit();
            return dtoConverter.toDTO(order);
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read order type!", e);
        }
        return null;
    }

    @Override
    public void create(OrderDTO orderDTO, Long bucketId) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Catalog item = itemDao.read(orderDTO.getItemId());
            User user = userDao.read(orderDTO.getUserId());

            Order order = new Order(user, item);
            order.setCreated(LocalDateTime.now());
            order.setQuantity(orderDTO.getQuantity());
            Bucket bucket = bucketDao.read(bucketId);
            bucket.getOrders().add(order);
            bucketDao.update(bucket);

            user.getItems().add(order);
            item.getUsers().add(order);

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
            List<Order> list = dao.getAll();
            transaction.commit();
            return dtoConverter.toDTOList(list);
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list orders!", e);
        }
        return null;
    }

    public List<OrderDTO> getById(Long userId) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("select U.items from User as U where U.id = :userId");
            query.setParameter("userId", userId);
            List<Order> list = query.getResultList();
            transaction.commit();
            return dtoConverter.toDTOList(list);
        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list orders!", e);
        }
        return null;
    }
}
