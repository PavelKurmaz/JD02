package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.dao.OrderDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order> implements OrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);
    public OrderDAOImpl() {
        super(Order.class);
    }
}
