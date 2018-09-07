package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.DTOConverter.AddressDTOConverter;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.beans.dto.AddressDTO;
import com.gmail.kurmazpavel.converter.AddressConverter;
import com.gmail.kurmazpavel.genericDAO.impl.AddressDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.GenericDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);
    private GenericDAOImpl dao = new AddressDAOImpl(Address.class);
    private AddressConverter converter = new AddressConverter();
    private AddressDTOConverter dtoConverter = new AddressDTOConverter();

    @Override
    public AddressDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Address address = (Address) dao.read(entityID);
            transaction.commit();
            return dtoConverter.toDTO(address);
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
