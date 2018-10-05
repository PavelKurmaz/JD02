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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
        return dtoConverter.toDTO(dao.read(entityID));
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        Address address = converter.toEntity(addressDTO);
        dao.update(address);
        return dtoConverter.toDTO(address);
    }

}
