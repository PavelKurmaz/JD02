package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    BucketDTO read(Long entityID);
    BucketDTO create(BucketDTO bucketDTO);
    BucketDTO update(BucketDTO bucketDTO);
    BucketDTO delete(BucketDTO bucketDTO);
    List<BucketDTO> getAll();
}
