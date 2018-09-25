package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.Address;
import com.gmail.kurmazpavel.dto.AddressDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("addressConverter")
public class AddressConverter implements Converter<AddressDTO, Address> {
    @Override
    public Address toEntity(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setBuilding(dto.getBuilding());
        address.setApt(dto.getApt());
        address.setZip(dto.getZip());
        return address;
    }

    @Override
    public List<Address> toEntityList(List<AddressDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
