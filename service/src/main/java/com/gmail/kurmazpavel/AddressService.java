package com.gmail.kurmazpavel;


import com.gmail.kurmazpavel.beans.dto.AddressDTO;

public interface AddressService {

    AddressDTO read(Long entityID);
    AddressDTO update(AddressDTO addressDTO);

}
