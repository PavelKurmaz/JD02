package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.Admin;
import com.gmail.kurmazpavel.dto.AdminDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AdminConverter implements Converter<AdminDTO, Admin> {
    private NewsConverter newsConverter = new NewsConverter();

    @Override
    public Admin toEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setEmail(dto.getEmail());
        admin.setLogin(dto.getLogin());
        admin.setPassword(dto.getPassword());
        admin.setPhone(dto.getPhone());
        admin.setNews(newsConverter.toEntityList(dto.getNews()));
        admin.setRoleId(dto.getRoleId());
        return admin;
    }

    @Override
    public List<Admin> toEntityList(List<AdminDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
