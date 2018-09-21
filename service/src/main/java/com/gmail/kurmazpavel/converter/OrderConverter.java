package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.OrderId;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter implements Converter<OrderDTO, Order> {

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        order.setBucketId(dto.getBucketId());
        order.setOrderId(new OrderId(dto.getUserId(), dto.getItemId()));
        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
