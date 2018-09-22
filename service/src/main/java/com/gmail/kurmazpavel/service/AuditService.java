package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.AuditDTO;
import java.util.List;

public interface AuditService {
    AuditDTO create(AuditDTO dto);
    void update(AuditDTO dto);
    List<AuditDTO> getAll();
}
