package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Catalog;
import com.gmail.kurmazpavel.Discount;
import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.dto.CatalogDTO;
import com.gmail.kurmazpavel.dto.DiscountDTO;
import com.gmail.kurmazpavel.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("catalogDTOConverter")
public class CatalogDTOConverter implements DTOConverter<CatalogDTO, Catalog> {
    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<OrderDTO, Order> orderDTOConverter;

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
