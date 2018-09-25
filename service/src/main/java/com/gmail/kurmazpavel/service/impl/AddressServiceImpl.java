package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.AddressService;
import com.gmail.kurmazpavel.Address;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.dao.AddressDao;
import com.gmail.kurmazpavel.service.converter.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);
    @Autowired
    private AddressDao dao;
    @Autowired
    @Qualifier("addressConverter")
    private Converter<AddressDTO, Address> converter;
    @Autowired
    @Qualifier("addressDTOConverter")
    private DTOConverter<AddressDTO, Address> dtoConverter;

    @Override
    public AddressDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            AddressDTO addressDTO = dtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return addressDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read address!", e);
        }
        return null;
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Address address = converter.toEntity(addressDTO);
            dao.update(address);
            transaction.commit();
            return dtoConverter.toDTO(address);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update address!", e);
        }
        return addressDTO;
    }
}
