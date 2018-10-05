package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO read(Long entityID);
    UserDTO create(UserDTO userDTO, String role);
    UserDTO update(UserDTO userDTO);
    UserDTO delete (UserDTO userDTO);
    List<UserDTO> getAll();
    UserDTO readByLogin(String login);
    UserDTO readByEmail(String email);
}
