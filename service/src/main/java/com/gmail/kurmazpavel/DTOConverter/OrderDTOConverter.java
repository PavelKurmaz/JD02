package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO order = new OrderDTO();
        order.setUserId(entity.getId().getUserId());
        order.setItemId(entity.getId().getItemId());
        order.setCreated(entity.getCreated());
        order.setQuantity(entity.getQuantity());
        return order;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
