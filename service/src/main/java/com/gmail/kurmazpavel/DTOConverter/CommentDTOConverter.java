package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Comment;
import com.gmail.kurmazpavel.beans.dto.CommentDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {
    private UserDTOConverter userDTOConverter = new UserDTOConverter();

    @Override
    public CommentDTO toDTO(Comment entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setContent(entity.getContent());
        commentDTO.setCreated(entity.getCreated());
        commentDTO.setNewsId(entity.getNewsId());
        commentDTO.setUserDTO(userDTOConverter.toDTO(entity.getUser()));
        return commentDTO;
    }

    @Override
    public List<CommentDTO> toDTOList(List<Comment> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
