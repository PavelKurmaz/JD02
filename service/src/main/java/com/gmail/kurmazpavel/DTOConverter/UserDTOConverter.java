package com.gmail.kurmazpavel.DTOConverter;

import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTOConverter implements DTOConverter<UserDTO, User> {
    private AddressDTOConverter addressDTOConverter = new AddressDTOConverter();
    private DiscountDTOConverter discountDTOConverter = new DiscountDTOConverter();
    private OrderDTOConverter orderDTOConverter = new OrderDTOConverter();

    @Override
    public UserDTO toDTO(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setEmail(entity.getEmail());
        userDTO.setLogin(entity.getLogin());
        userDTO.setPassword(entity.getPassword());
        userDTO.setPhone(entity.getPhone());
        userDTO.setDisabled(entity.isDisabled());
        if (entity.getDiscount() != null)
            userDTO.setDiscount(discountDTOConverter.toDTO(entity.getDiscount()));
        userDTO.setAddress(addressDTOConverter.toDTO(entity.getAddress()));
        userDTO.setRoleId(entity.getRoleId());
        userDTO.setOrders(orderDTOConverter.toDTOList(entity.getOrders()));
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
