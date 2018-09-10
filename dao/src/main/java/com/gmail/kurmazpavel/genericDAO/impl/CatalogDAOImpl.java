package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.genericDAO.CatalogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CatalogDAOImpl extends GenericDAOImpl<Catalog> implements CatalogDao{
    private static final Logger logger = LogManager.getLogger(CatalogDAOImpl.class);
    public CatalogDAOImpl(Class<Catalog> catalogClass) {
        super(catalogClass);
    }
}
