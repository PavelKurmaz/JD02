package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Bucket;
import com.gmail.kurmazpavel.dao.BucketDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class BucketDAOImpl extends GenericDAOImpl<Bucket> implements BucketDao {
    private static final Logger logger = LogManager.getLogger(BucketDAOImpl.class);
    public BucketDAOImpl() {
        super(Bucket.class);
    }
}
