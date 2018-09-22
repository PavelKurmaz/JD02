package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.News;
import com.gmail.kurmazpavel.dto.NewsDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class NewsConverter implements Converter<NewsDTO, News>{
    private CommentConverter commentConverter = new CommentConverter();

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
