package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.Catalog;
import com.gmail.kurmazpavel.dto.CatalogDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CatalogConverter implements Converter<CatalogDTO, Catalog> {
    private OrderConverter orderConverter = new OrderConverter();

    @Override
    public Catalog toEntity(CatalogDTO dto) {
        Catalog catalog = new Catalog();
        catalog.setId(dto.getId());
        catalog.setAmount(dto.getAmount());
        catalog.setName(dto.getName());
        catalog.setPrice(dto.getPrice());
        catalog.setOrders(orderConverter.toEntityList(dto.getOrders()));
        return catalog;
    }

    @Override
    public List<Catalog> toEntityList(List<CatalogDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
