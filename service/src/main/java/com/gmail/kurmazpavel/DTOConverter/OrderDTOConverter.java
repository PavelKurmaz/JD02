package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO order = new OrderDTO();
        order.setId(entity.getId());
        order.setCompleted(entity.getCompleted());
        order.setUsers_ID(entity.getUsers_ID());
        return order;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
