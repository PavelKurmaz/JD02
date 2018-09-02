package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AuditDTOConverter implements DTOConverter<AuditDTO, Audit> {
    @Override
    public AuditDTO toDTO(Audit entity) {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setID(entity.getId());
        auditDTO.setEvent_type(entity.getEvent_type());
        auditDTO.setLocalDateTime(entity.getCreated());
        return auditDTO;
    }

    @Override
    public List<AuditDTO> toDTOList(List<Audit> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
