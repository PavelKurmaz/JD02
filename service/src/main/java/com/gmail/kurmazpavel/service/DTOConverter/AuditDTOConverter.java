package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Audit;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.AuditDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component("auditDTOConverter")
public class AuditDTOConverter implements DTOConverter<AuditDTO, Audit> {
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter = new UserDTOConverter();

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
