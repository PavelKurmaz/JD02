package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.ShippinglistDTOConverter;
import com.gmail.kurmazpavel.ListService;
import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.beans.dto.ShippingListDTO;
import com.gmail.kurmazpavel.converter.ShippinglistConverter;
import com.gmail.kurmazpavel.genericDAO.GenericDAOImpl;
import com.gmail.kurmazpavel.genericDAO.ShippinglistDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ListServiceImpl implements ListService {

    private static final Logger logger = LogManager.getLogger(ListServiceImpl.class);
    private GenericDAOImpl dao = new ShippinglistDAOImpl(ShippingList.class);
    private ShippinglistConverter converter = new ShippinglistConverter();
    private ShippinglistDTOConverter dtoConverter = new ShippinglistDTOConverter();

    @Override
    public ShippingListDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            ShippingList shippingList = (ShippingList) dao.read(entityID);
            transaction.commit();
            return dtoConverter.toDTO(shippingList);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read shippingList type!", e);
        }
        return null;
    }

    @Override
    public ShippingListDTO create(ShippingListDTO shippingListDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            ShippingList shippingList = converter.toEntity(shippingListDTO);
            shippingList.setId(null);
            dao.create(shippingList);
            transaction.commit();
            return dtoConverter.toDTO(shippingList);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create shippingList type!", e);
        }
        return shippingListDTO;
    }

    @Override
    public ShippingListDTO update(ShippingListDTO shippingListDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            ShippingList shippingList = converter.toEntity(shippingListDTO);
            dao.update(shippingList);
            transaction.commit();
            return dtoConverter.toDTO(shippingList);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update shippingList type!", e);
        }
        return shippingListDTO;
    }

    @Override
    public ShippingListDTO delete(ShippingListDTO shippingListDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            ShippingList shippingList = converter.toEntity(shippingListDTO);
            dao.delete(shippingList);
            transaction.commit();
            return dtoConverter.toDTO(shippingList);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete shippingList type!", e);
        }
        return shippingListDTO;
    }

    @Override
    public List<ShippingListDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<ShippingList> list = dao.getAll();
            transaction.commit();
            return dtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list shippingLists!", e);
        }
        return null;
    }
}
