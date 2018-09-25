package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Comment;
import com.gmail.kurmazpavel.News;
import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("newsDTOConverter")
public class NewsDTOConverter implements DTOConverter<NewsDTO, News> {
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Override
    public NewsDTO toDTO(News entity) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(entity.getId());
        newsDTO.setContent(entity.getContent());
        newsDTO.setTitle(entity.getTitle());
        newsDTO.setCreated(entity.getCreated());
        newsDTO.setUserId(entity.getUserId());
        newsDTO.setComments(commentDTOConverter.toDTOList(entity.getComments()));
        return newsDTO;
    }

    @Override
    public List<NewsDTO> toDTOList(List<News> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
