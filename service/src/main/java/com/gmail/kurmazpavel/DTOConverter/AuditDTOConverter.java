package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.Audit;
import com.gmail.kurmazpavel.dto.AuditDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AuditDTOConverter implements DTOConverter<AuditDTO, Audit> {
    private UserDTOConverter userDTOConverter = new UserDTOConverter();

    @Override
    public AuditDTO toDTO(Audit entity) {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setCreated(entity.getCreated());
        auditDTO.setEvent(entity.getEvent());
        auditDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        auditDTO.setId(entity.getId());
        return auditDTO;
    }

    @Override
    public List<AuditDTO> toDTOList(List<Audit> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
