package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO read(Long entityID);
    void create(OrderDTO orderDTO, Long bucketId);
    List<OrderDTO> getAll();
    List<OrderDTO> getById(Long userId);
}
