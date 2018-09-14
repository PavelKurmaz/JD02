package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Discount;
import com.gmail.kurmazpavel.genericDAO.DiscountDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscountDAOImpl extends GenericDAOImpl<Discount> implements DiscountDao {
    private static final Logger logger = LogManager.getLogger(DiscountDAOImpl.class);
    public DiscountDAOImpl(Class<Discount> discountClass) {
        super(discountClass);
    }
}
