package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.ShippingList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShippinglistDAOImpl extends GenericDAOImpl<ShippingList>{
    private static final Logger logger = LogManager.getLogger(ShippinglistDAOImpl.class);
    public ShippinglistDAOImpl(Class<ShippingList> listClass) {
        super(listClass);
    }
}
