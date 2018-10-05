package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.*;
import com.gmail.kurmazpavel.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<UserDTO, User> {
    @Autowired
    @Qualifier("addressDTOConverter")
    private DTOConverter<AddressDTO, Address> addressDTOConverter;
    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<OrderDTO, Order> orderDTOConverter;
    @Autowired
    @Qualifier("newsDTOConverter")
    private DTOConverter<NewsDTO, News> newsDTOConverter;

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
        userDTO.setNews(newsDTOConverter.toDTOList(entity.getNews()));
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
