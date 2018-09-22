package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.Comment;
import com.gmail.kurmazpavel.dto.CommentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CommentConverter implements Converter<CommentDTO, Comment>{
    private UserConverter userConverter = new UserConverter();

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());
        comment.setNewsId(dto.getNewsId());
        comment.setUser(userConverter.toEntity(dto.getUserDTO()));
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
