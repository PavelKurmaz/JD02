package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogDTOConverter implements DTOConverter<CatalogDTO, Catalog> {

    @Override
    public CatalogDTO toDTO(Catalog entity) {
        CatalogDTO catalog = new CatalogDTO();
        catalog.setID(entity.getID());
        catalog.setAmount(entity.getAmount());
        catalog.setName(entity.getName());
        catalog.setPrice(entity.getPrice());
        return catalog;
    }

    @Override
    public List<CatalogDTO> toDTOList(List<Catalog> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
