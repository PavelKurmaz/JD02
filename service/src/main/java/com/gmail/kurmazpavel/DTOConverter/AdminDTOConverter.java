package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDTOConverter implements DTOConverter<AdminDTO, Admin> {

    @Override
    public AdminDTO toDTO(Admin entity) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(entity.getId());
        adminDTO.setEmail(entity.getEmail());
        adminDTO.setLogin(entity.getLogin());
        adminDTO.setPassword(entity.getPassword());
        adminDTO.setPhone(entity.getPhone());
        adminDTO.setRoles_id(entity.getRoles_id());
        return adminDTO;
    }

    @Override
    public List<AdminDTO> toDTOList(List<Admin> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
