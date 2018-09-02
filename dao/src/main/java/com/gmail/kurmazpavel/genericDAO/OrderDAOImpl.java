package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDAOImpl extends GenericDAOImpl<Order>{
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);
    public OrderDAOImpl(Class<Order> orderClass) {
        super(orderClass);
    }
}
