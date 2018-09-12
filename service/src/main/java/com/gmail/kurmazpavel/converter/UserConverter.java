package com.gmail.kurmazpavel.converter;


import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter implements Converter<UserDTO, User> {
    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setCarma(dto.getCarma());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setRoles_id(dto.getRolesId());
        user.setDisabled(dto.getDisabled());
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
