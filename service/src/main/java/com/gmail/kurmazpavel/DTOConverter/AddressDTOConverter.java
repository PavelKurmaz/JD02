package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.beans.dto.AddressDTO;
import java.util.List;
import java.util.stream.Collectors;

public class AddressDTOConverter implements DTOConverter<AddressDTO, Address> {

    @Override
    public AddressDTO toDTO(Address entity) {
        AddressDTO address = new AddressDTO();
        address.setId(entity.getId());
        address.setCountry(entity.getCountry());
        address.setCity(entity.getCity());
        address.setStreet(entity.getStreet());
        address.setBuilding(entity.getBuilding());
        address.setApt(entity.getApt());
        address.setZip(entity.getZip());
        return address;
    }

    @Override
    public List<AddressDTO> toDTOList(List<Address> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}