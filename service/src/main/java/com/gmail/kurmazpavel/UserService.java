package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO read(Long entityID);
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO delete (UserDTO userDTO);
    List<UserDTO> getAll();
    UserDTO readByLogin(String login);
    UserDTO readByEmail(String email);
    UserDTO checkPermission(UserDTO userDTO, String permissionName);
}
