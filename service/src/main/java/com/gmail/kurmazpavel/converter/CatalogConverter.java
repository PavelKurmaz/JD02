package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogConverter implements Converter<CatalogDTO, Catalog> {
    @Override
    public Catalog toEntity(CatalogDTO dto) {
        Catalog catalog = new Catalog();
        catalog.setId(dto.getID());
        catalog.setAmount(dto.getAmount());
        catalog.setName(dto.getName());
        catalog.setPrice(dto.getPrice());
        return catalog;
    }

    @Override
    public List<Catalog> toEntityList(List<CatalogDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
