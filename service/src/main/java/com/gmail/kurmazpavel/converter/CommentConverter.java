package com.gmail.kurmazpavel.converter;


import com.gmail.kurmazpavel.beans.Comment;
import com.gmail.kurmazpavel.beans.dto.CommentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CommentConverter implements Converter<CommentDTO, Comment>{

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());
        comment.setUser_id(dto.getUser_id());
        comment.setNews_id(dto.getNews_id());
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
