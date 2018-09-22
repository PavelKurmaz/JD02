package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.AdminDTO;
import java.util.List;

public interface AdminService {

    AdminDTO read(Long entityID);
    AdminDTO readByLogin(String login);
    AdminDTO create(AdminDTO adminDTO, String roleName);
    AdminDTO update(AdminDTO adminDTO);
    AdminDTO delete (AdminDTO adminDTO);
    List<AdminDTO> getAll();
}
