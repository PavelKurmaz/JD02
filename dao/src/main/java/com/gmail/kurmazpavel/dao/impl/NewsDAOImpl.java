package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.News;
import com.gmail.kurmazpavel.dao.NewsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDAOImpl extends GenericDAOImpl<News> implements NewsDao {
    private static final Logger logger = LogManager.getLogger(NewsDAOImpl.class);
    public NewsDAOImpl() {
        super(News.class);
    }
}
