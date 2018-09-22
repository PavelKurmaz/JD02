package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.Catalog;
import com.gmail.kurmazpavel.dto.CatalogDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CatalogDTOConverter implements DTOConverter<CatalogDTO, Catalog> {
    private DiscountDTOConverter discountDTOConverter = new DiscountDTOConverter();
    private OrderDTOConverter orderDTOConverter = new OrderDTOConverter();

    @Override
    public CatalogDTO toDTO(Catalog entity) {
        CatalogDTO catalog = new CatalogDTO();
        catalog.setId(entity.getId());
        catalog.setAmount(entity.getAmount());
        catalog.setName(entity.getName());
        catalog.setPrice(entity.getPrice());
        catalog.setDiscounts(discountDTOConverter.toDTOList(entity.getDiscounts()));
        catalog.setOrders(orderDTOConverter.toDTOList(entity.getOrders()));
        return catalog;
    }

    @Override
    public List<CatalogDTO> toDTOList(List<Catalog> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
