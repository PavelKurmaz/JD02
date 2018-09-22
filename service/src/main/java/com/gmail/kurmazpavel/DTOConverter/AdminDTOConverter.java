package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.Admin;
import com.gmail.kurmazpavel.dto.AdminDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AdminDTOConverter implements DTOConverter<AdminDTO, Admin> {
    private NewsDTOConverter newsDTOConverter = new NewsDTOConverter();

    @Override
    public AdminDTO toDTO(Admin entity) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(entity.getId());
        adminDTO.setEmail(entity.getEmail());
        adminDTO.setLogin(entity.getLogin());
        adminDTO.setPassword(entity.getPassword());
        adminDTO.setPhone(entity.getPhone());
        adminDTO.setRoleId(entity.getRoleId());
        adminDTO.setNews(newsDTOConverter.toDTOList(entity.getNews()));
        return adminDTO;
    }

    @Override
    public List<AdminDTO> toDTOList(List<Admin> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
