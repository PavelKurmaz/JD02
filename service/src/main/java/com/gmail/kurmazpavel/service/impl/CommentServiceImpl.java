package com.gmail.kurmazpavel.service.impl;

import com.gmail.kurmazpavel.service.CommentService;
import com.gmail.kurmazpavel.Comment;
import com.gmail.kurmazpavel.News;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.service.DTOConverter.DTOConverter;
import com.gmail.kurmazpavel.dao.CommentDao;
import com.gmail.kurmazpavel.dao.NewsDao;
import com.gmail.kurmazpavel.service.converter.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentDao dao;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<CommentDTO, Comment> commentDtoConverter;

    @Override
    public CommentDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            CommentDTO comment = commentDtoConverter.toDTO(dao.read(entityID));
            transaction.commit();
            return comment;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read comment type!", e);
        }
        return new CommentDTO();
    }

    @Override
    public CommentDTO create(CommentDTO commentDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Comment comment = commentConverter.toEntity(commentDTO);
            News news = newsDao.read(commentDTO.getNewsId());
            news.getComments().add(comment);
            User user = comment.getUser();
            user.getComments().add(comment);
            dao.save(comment);
            commentDTO = commentDtoConverter.toDTO(comment);
            transaction.commit();
            return commentDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to create comment type!", e);
        }
        return commentDTO;
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Comment comment = dao.read(commentDTO.getId());
            // some update 
            dao.update(comment);
            transaction.commit();
            return commentDtoConverter.toDTO(comment);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to update comment type!", e);
        }
        return commentDTO;
    }

    @Override
    public CommentDTO delete(CommentDTO commentDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Comment comment = commentConverter.toEntity(commentDTO);
            dao.delete(comment);
            commentDTO = commentDtoConverter.toDTO(comment);
            transaction.commit();
            return commentDTO;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to delete comment type!", e);
        }
        return commentDTO;
    }

    @Override
    public List<CommentDTO> getAll() {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            List<CommentDTO> list = commentDtoConverter.toDTOList(dao.getAll());
            transaction.commit();
            return list;
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list comments!", e);
        }
        return new ArrayList<>();
    }
}