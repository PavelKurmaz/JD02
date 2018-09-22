package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Discount;
import com.gmail.kurmazpavel.dao.DiscountDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDAOImpl extends GenericDAOImpl<Discount> implements DiscountDao {
    private static final Logger logger = LogManager.getLogger(DiscountDAOImpl.class);
    public DiscountDAOImpl() {
        super(Discount.class);
    }
}
