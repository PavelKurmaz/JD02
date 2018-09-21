package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.beans.Bucket;
import com.gmail.kurmazpavel.beans.dto.BucketDTO;
import java.util.List;
import java.util.stream.Collectors;

public class BucketConverter implements Converter<BucketDTO, Bucket> {
    private OrderConverter orderConverter = new OrderConverter();

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
