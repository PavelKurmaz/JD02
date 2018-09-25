package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.Audit;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.AuditDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("auditConverter")
public class AuditConverter implements Converter<AuditDTO, Audit> {
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;

    @Override
    public Audit toEntity(AuditDTO dto) {
        Audit audit = new Audit();
        audit.setUser(userConverter.toEntity(dto.getUser()));
        audit.setId(dto.getId());
        audit.setCreated(dto.getCreated());
        audit.setEvent(dto.getEvent());
        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}