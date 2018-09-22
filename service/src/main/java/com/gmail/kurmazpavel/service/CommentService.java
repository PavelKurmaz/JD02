package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO read(Long entityID);
    CommentDTO create(CommentDTO commentDTO);
    CommentDTO update(CommentDTO commentDTO);
    CommentDTO delete(CommentDTO commentDTO);
    List<CommentDTO> getAll();
}
