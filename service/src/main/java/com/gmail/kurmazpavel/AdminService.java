package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import java.util.List;

public interface AdminService {

    AdminDTO read(Long entityID);
    AdminDTO readByLogin(String login);
    AdminDTO create(AdminDTO adminDTO);
    AdminDTO update(AdminDTO adminDTO);
    AdminDTO delete (AdminDTO adminDTO);
    List<AdminDTO> getAll();
}
