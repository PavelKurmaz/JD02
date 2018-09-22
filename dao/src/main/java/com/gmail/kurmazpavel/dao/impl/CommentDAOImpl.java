package com.gmail.kurmazpavel.dao.impl;

import com.gmail.kurmazpavel.Comment;
import com.gmail.kurmazpavel.dao.CommentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDao {
    private static final Logger logger = LogManager.getLogger(CommentDAOImpl.class);
    public CommentDAOImpl() {
        super(Comment.class);
    }
}
