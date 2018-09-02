package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressDAOImpl extends GenericDAOImpl<Address>{
    private static final Logger logger = LogManager.getLogger(AddressDAOImpl.class);
    public AddressDAOImpl(Class<Address> userClass) {
        super(userClass);
    }
}
