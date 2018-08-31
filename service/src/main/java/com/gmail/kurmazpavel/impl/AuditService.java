package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import java.util.List;

public interface AuditService {
    AuditDTO create(AuditDTO dto);
    List<AuditDTO> getAll();

}
