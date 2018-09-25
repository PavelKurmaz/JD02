package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.service.NewsService;
import com.gmail.kurmazpavel.Admin;
import com.gmail.kurmazpavel.News;
import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.dto.NewsDTO;
import com.gmail.kurmazpavel.service.converter.Converter;
import com.gmail.kurmazpavel.dao.AdminDao;
import com.gmail.kurmazpavel.dao.NewsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    @Autowired
    private NewsDao dao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    @Qualifier("newsConverter")
    private Converter<NewsDTO, News> newsConverter;
    @Autowired
    @Qualifier("newsDTOConverter")
    private DTOConverter<NewsDTO, News> newsDtoConverter;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter commentDTOConverter;

    @Override
    public NewsDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            NewsDTO news = newsDtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return news;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read news type!", e);
        }
        return new NewsDTO();
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            News news = newsConverter.toEntity(newsDTO);
            Admin admin = adminDao.read(newsDTO.getUserId());
            admin.getNews().add(news);
            adminDao.update(admin);
            newsDTO = newsDtoConverter.toDTO(news);
            transaction.commit();
            return newsDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create news type!", e);
        }
        return newsDTO;
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            News news = dao.read(newsDTO.getId());
            news.setContent(newsDTO.getContent());
            news.setTitle(newsDTO.getTitle());
            dao.update(news);
            newsDTO = newsDtoConverter.toDTO(news);
            transaction.commit();
            return newsDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update news type!", e);
        }
        return newsDTO;
    }

    @Override
    public NewsDTO delete(NewsDTO newsDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            News news = newsConverter.toEntity(newsDTO);
            dao.delete(news);
            newsDTO = newsDtoConverter.toDTO(news);
            transaction.commit();
            return newsDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete news type!", e);
        }
        return newsDTO;
    }

    @Override
    public List<NewsDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<NewsDTO> list = newsDtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return list;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list news!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<CommentDTO> getComments(Long id) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Query query = session.createQuery("select n.comments from News as n where n.id=:id");
            query.setParameter("id", id);
            List<CommentDTO> comments = commentDTOConverter.toDTOList(query.getResultList());
            transaction.commit();
            return comments;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list comments!", e);
        }
        return new ArrayList<>();
    }
}