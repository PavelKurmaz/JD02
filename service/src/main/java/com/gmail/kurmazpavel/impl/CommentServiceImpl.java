package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DTOConverter.CommentDTOConverter;
import com.gmail.kurmazpavel.CommentService;
import com.gmail.kurmazpavel.beans.Comment;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.CommentDTO;
import com.gmail.kurmazpavel.converter.CommentConverter;
import com.gmail.kurmazpavel.genericDAO.CommentDao;
import com.gmail.kurmazpavel.genericDAO.UserDao;
import com.gmail.kurmazpavel.genericDAO.impl.CommentDAOImpl;
import com.gmail.kurmazpavel.genericDAO.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDao dao = new CommentDAOImpl(Comment.class);
    private UserDao userDao = new UserDAOImpl(User.class);
    private CommentConverter commentConverter = new CommentConverter();
    private CommentDTOConverter commentDtoConverter = new CommentDTOConverter();

    @Override
    public CommentDTO read(Long entityID) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Comment comment = dao.read(entityID);
            transaction.commit();
            return commentDtoConverter.toDTO(comment);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to read comment type!", e);
        }
        return null;
    }

    @Override
    public CommentDTO create(CommentDTO commentDTO) {
        Session session = dao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive())
                session.beginTransaction();
            Comment comment = commentConverter.toEntity(commentDTO);
            comment.setId(null);
            User user = userDao.read(commentDTO.getUser_id());
            user.getCommentList().add(comment);
            userDao.update(user);
            transaction.commit();
            return commentDtoConverter.toDTO(comment);
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
            transaction.commit();
            return commentDtoConverter.toDTO(comment);
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
            List<Comment> list = dao.getAll();
            transaction.commit();
            return commentDtoConverter.toDTOList(list);
        }
        catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            logger.error("Failed to list comments!", e);
        }
        return null;
    }
}