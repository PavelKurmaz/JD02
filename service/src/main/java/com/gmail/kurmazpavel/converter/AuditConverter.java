package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.Audit;
import com.gmail.kurmazpavel.dto.AuditDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AuditConverter implements Converter<AuditDTO, Audit> {
    private UserConverter userConverter = new UserConverter();

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