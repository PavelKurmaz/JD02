package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.Bucket;
import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.dto.BucketDTO;
import com.gmail.kurmazpavel.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("bucketConverter")
public class BucketConverter implements Converter<BucketDTO, Bucket> {
    @Autowired
    @Qualifier("orderConverter")
    private Converter<OrderDTO, Order> orderConverter;

    @Override
    public Bucket toEntity(BucketDTO dto) {
        Bucket bucket = new Bucket();
        bucket.setId(dto.getId());
        bucket.setStatus(dto.getStatus());
        bucket.setUserId(dto.getUserId());
        bucket.setCreated(dto.getCreated());
        bucket.setOrders(orderConverter.toEntityList(dto.getOrders()));
        return bucket;
    }

    @Override
    public List<Bucket> toEntityList(List<BucketDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
