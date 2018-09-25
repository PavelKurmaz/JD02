package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Catalog;
import com.gmail.kurmazpavel.dao.CatalogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogDAOImpl extends GenericDAOImpl<Catalog> implements CatalogDao{
    private static final Logger logger = LogManager.getLogger(CatalogDAOImpl.class);
    public CatalogDAOImpl() {
        super(Catalog.class);
    }
}
