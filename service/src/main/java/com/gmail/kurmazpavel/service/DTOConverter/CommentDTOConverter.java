package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Comment;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("commentDTOConverter")
public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter;

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
