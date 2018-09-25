package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Bucket;
import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.dto.BucketDTO;
import com.gmail.kurmazpavel.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component("bucketDTOConverter")
public class BucketDTOConverter implements DTOConverter<BucketDTO, Bucket> {
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<OrderDTO, Order> orderDTOConverter;

    @Override
    public BucketDTO toDTO(Bucket entity) {
        BucketDTO bucket = new BucketDTO();
        bucket.setId(entity.getId());
        bucket.setStatus(entity.getStatus());
        bucket.setUserId(entity.getUserId());
        bucket.setCreated(entity.getCreated());
        bucket.setOrders(orderDTOConverter.toDTOList(entity.getOrders()));
        return bucket;
    }

    @Override
    public List<BucketDTO> toDTOList(List<Bucket> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
