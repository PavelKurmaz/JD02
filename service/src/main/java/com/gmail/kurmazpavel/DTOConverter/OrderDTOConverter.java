package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO order = new OrderDTO();
        order.setCreated(entity.getCreated());
        order.setQuantity(entity.getQuantity());
        order.setBucketId(entity.getBucketId());
        order.setUserId(entity.getOrderId().getUserId());
        order.setItemId(entity.getOrderId().getItemId());
        return order;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
