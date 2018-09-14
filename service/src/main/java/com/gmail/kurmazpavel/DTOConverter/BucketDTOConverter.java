package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Bucket;
import com.gmail.kurmazpavel.beans.dto.BucketDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BucketDTOConverter implements DTOConverter<BucketDTO, Bucket> {

    @Override
    public BucketDTO toDTO(Bucket entity) {
        BucketDTO bucket = new BucketDTO();
        bucket.setId(entity.getId());
        bucket.setStatus(entity.getStatus());
        bucket.setUserId(entity.getUserId());
        bucket.setCreated(entity.getCreated());
        return bucket;
    }

    @Override
    public List<BucketDTO> toDTOList(List<Bucket> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
