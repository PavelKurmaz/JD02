package com.gmail.kurmazpavel.converter;


import com.gmail.kurmazpavel.beans.Audit;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import java.util.List;
import java.util.stream.Collectors;

public class AuditConverter implements Converter<AuditDTO, Audit> {

    @Override
    public Audit toEntity(AuditDTO dto) {
        Audit audit = new Audit();
        audit.setId(dto.getID());
        audit.setUsers_ID(dto.getUsers_ID());
        audit.setEvent_type(dto.getEvent_type());
        audit.setCreated(dto.getLocalDateTime());
        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}