package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    NewsDTO read(Long entityId);
    NewsDTO create(NewsDTO newsDTO);
    NewsDTO update(NewsDTO newsDTO);
    NewsDTO delete(NewsDTO newsDTO);
    List<NewsDTO> getAll();
    List<CommentDTO> getComments(Long entityId);
}
