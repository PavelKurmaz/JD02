package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.beans.dto.ShippingListDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ShippinglistDTOConverter implements DTOConverter<ShippingListDTO, ShippingList> {

    @Override
    public ShippingListDTO toDTO(ShippingList entity) {
        ShippingListDTO shippingList = new ShippingListDTO();
        shippingList.setCatalog_ID(entity.getCatalog_ID());
        shippingList.setId(entity.getId());
        shippingList.setOrder_ID(entity.getOrder_ID());
        shippingList.setQuantity(entity.getQuantity());
        return shippingList;
    }

    @Override
    public List<ShippingListDTO> toDTOList(List<ShippingList> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
