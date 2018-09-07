package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.News;
import com.gmail.kurmazpavel.genericDAO.NewsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewsDAOImpl extends GenericDAOImpl<News> implements NewsDao {
    private static final Logger logger = LogManager.getLogger(NewsDAOImpl.class);
    public NewsDAOImpl(Class<News> newsClass) {
        super(newsClass);
    }
}
