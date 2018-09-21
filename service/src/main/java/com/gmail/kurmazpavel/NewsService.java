package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.CommentDTO;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    NewsDTO read(Long entityId);
    NewsDTO create(NewsDTO newsDTO);
    NewsDTO update(NewsDTO newsDTO);
    NewsDTO delete(NewsDTO newsDTO);
    List<NewsDTO> getAll();
    List<CommentDTO> getComments(Long entityId);
}
