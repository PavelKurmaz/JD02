package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Address;
import com.gmail.kurmazpavel.dao.AddressDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address> implements AddressDao{
    private static final Logger logger = LogManager.getLogger(AddressDAOImpl.class);
    public AddressDAOImpl() {
        super(Address.class);
    }
}
