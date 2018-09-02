package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.OrderDTOConverter;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.converter.OrderConverter;
import com.gmail.kurmazpavel.genericDAO.OrderDAOImpl;
import com.gmail.kurmazpavel.genericDAO.GenericDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private GenericDAOImpl dao = new OrderDAOImpl(Order.class);
    private OrderConverter converter = new OrderConverter();
    private OrderDTOConverter dtoConverter = new OrderDTOConverter();
    
    @Override
    public OrderDTO read(Long entityID) {
    Session session = dao.getCurrentSession();
        try {
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive())
            session.beginTransaction();
        Order order = (Order) dao.read(entityID);
        transaction.commit();
        return dtoConverter.toDTO(order);
    }
        catch (Exception e) {
        if (session.getTransaction().isActive())
            session.getTransaction().rollback();
        logger.error("Failed to read order type!", e);
    }
        return null;
}

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Order order = converter.toEntity(orderDTO);
            order.setId(null);
            dao.create(order);
            transaction.commit();
            return dtoConverter.toDTO(order);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create order type!", e);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Order order = converter.toEntity(orderDTO);
            dao.update(order);
            transaction.commit();
            return dtoConverter.toDTO(order);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update order type!", e);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO delete(OrderDTO orderDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Order order = converter.toEntity(orderDTO);
            dao.delete(order);
            transaction.commit();
            return dtoConverter.toDTO(order);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete order type!", e);
        }
        return orderDTO;
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
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list orders!", e);
        }
        return null;
    }
}
