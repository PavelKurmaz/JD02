package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.beans.dto.ShippingListDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ShippinglistConverter implements Converter<ShippingListDTO, ShippingList> {
    @Override
    public ShippingList toEntity(ShippingListDTO dto) {
        ShippingList shippingList = new ShippingList();
        shippingList.setCatalog_ID(dto.getCatalog_ID());
        shippingList.setId(dto.getId());
        shippingList.setOrder_ID(dto.getOrder_ID());
        shippingList.setQuantity(dto.getQuantity());
        return shippingList;
    }

    @Override
    public List<ShippingList> toEntityList(List<ShippingListDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
