package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.NewsDTOConverter;
import com.gmail.kurmazpavel.NewsService;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.beans.News;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;
import com.gmail.kurmazpavel.converter.NewsConverter;
import com.gmail.kurmazpavel.genericDAO.AdminDao;
import com.gmail.kurmazpavel.genericDAO.NewsDao;
import com.gmail.kurmazpavel.genericDAO.impl.AdminDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.NewsDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private NewsDao dao = new NewsDAOImpl(News.class);
    private AdminDao adminDao = new AdminDAOImpl(Admin.class);
    private NewsConverter newsConverter = new NewsConverter();
    private NewsDTOConverter newsDtoConverter = new NewsDTOConverter();

    @Override
    public NewsDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            News news = dao.read(entityID);
            transaction.commit();
            return newsDtoConverter.toDTO(news);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read news type!", e);
        }
        return null;
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            News news = newsConverter.toEntity(newsDTO);
            news.setId(null);
            Admin admin = adminDao.read(newsDTO.getUser_id());
            admin.getNewsList().add(news);
            adminDao.update(admin);
            transaction.commit();
            return newsDtoConverter.toDTO(news);
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
            // some update 
            dao.update(news);
            transaction.commit();
            return newsDtoConverter.toDTO(news);
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
            transaction.commit();
            return newsDtoConverter.toDTO(news);
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
            List<News> list = dao.getAll();
            transaction.commit();
            return newsDtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list newss!", e);
        }
        return null;
    }
}