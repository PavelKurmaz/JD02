package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setCarma(entity.getCarma());
        userDTO.setEmail(entity.getEmail());
        userDTO.setLogin(entity.getLogin());
        userDTO.setPassword(entity.getPassword());
        userDTO.setPhone(entity.getPhone());
        userDTO.setRolesId(entity.getRoles_id());
        userDTO.setDisabled(entity.getDisabled());
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
