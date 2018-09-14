package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Bucket;
import com.gmail.kurmazpavel.genericDAO.BucketDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BucketDAOImpl extends GenericDAOImpl<Bucket> implements BucketDao {
    private static final Logger logger = LogManager.getLogger(BucketDAOImpl.class);
    public BucketDAOImpl(Class<Bucket> bucketClass) {
        super(bucketClass);
    }
}
