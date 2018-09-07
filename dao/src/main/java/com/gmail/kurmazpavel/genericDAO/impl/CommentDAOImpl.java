package com.gmail.kurmazpavel.genericDAO.impl;

import com.gmail.kurmazpavel.beans.Comment;
import com.gmail.kurmazpavel.genericDAO.CommentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDao {
    private static final Logger logger = LogManager.getLogger(CommentDAOImpl.class);
    public CommentDAOImpl(Class<Comment> comClass) {
        super(comClass);
    }
}
