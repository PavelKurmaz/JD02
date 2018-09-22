package com.gmail.kurmazpavel.service;


import com.gmail.kurmazpavel.dto.AddressDTO;

public interface AddressService {

    AddressDTO read(Long entityID);
    AddressDTO update(AddressDTO addressDTO);

}
