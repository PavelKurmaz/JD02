package com.gmail.kurmazpavel.genericDAO;

import com.gmail.kurmazpavel.beans.Catalog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CatalogDAOImpl extends GenericDAOImpl<Catalog>{
    private static final Logger logger = LogManager.getLogger(CatalogDAOImpl.class);
    public CatalogDAOImpl(Class<Catalog> catalogClass) {
        super(catalogClass);
    }
}
