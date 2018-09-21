package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.News;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;
import java.util.List;
import java.util.stream.Collectors;

public class NewsDTOConverter implements DTOConverter<NewsDTO, News> {
    private CommentDTOConverter commentDTOConverter = new CommentDTOConverter();

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
