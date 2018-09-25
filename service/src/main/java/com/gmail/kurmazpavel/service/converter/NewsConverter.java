package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.Comment;
import com.gmail.kurmazpavel.News;
import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDTO, News>{
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;

    @Override
    public News toEntity(NewsDTO dto) {
        News news = new News();
        news.setId(dto.getId());
        news.setContent(dto.getContent());
        news.setTitle(dto.getTitle());
        news.setCreated(dto.getCreated());
        news.setUserId(dto.getUserId());
        news.setComments(commentConverter.toEntityList(dto.getComments()));
        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
