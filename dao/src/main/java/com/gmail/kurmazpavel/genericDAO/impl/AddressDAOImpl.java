package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.genericDAO.AddressDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressDAOImpl extends GenericDAOImpl<Address> implements AddressDao{
    private static final Logger logger = LogManager.getLogger(AddressDAOImpl.class);
    public AddressDAOImpl(Class<Address> userClass) {
        super(userClass);
    }
}
