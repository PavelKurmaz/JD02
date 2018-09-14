package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.DiscountDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO read(Long entityID);
    DiscountDTO getDiscount(UserDTO userDTO);
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO delete (UserDTO userDTO);
    List<UserDTO> getAll();
    UserDTO readByLogin(String login);
    UserDTO readByEmail(String email);
}
