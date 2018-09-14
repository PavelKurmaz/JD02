package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO read(Long entityID);
    void create(OrderDTO orderDTO, Long bucketId);
    OrderDTO simpleCreate(OrderDTO orderDTO);
    List<OrderDTO> getAll();
    List<OrderDTO> getById(Long userId);
}
