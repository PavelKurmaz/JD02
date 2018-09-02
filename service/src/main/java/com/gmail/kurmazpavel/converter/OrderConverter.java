package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter implements Converter<OrderDTO, Order> {
    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCompleted(dto.getCompleted());
        order.setUsers_ID(dto.getUsers_ID());
        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
