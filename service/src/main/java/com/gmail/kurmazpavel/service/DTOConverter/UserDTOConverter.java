package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Address;
import com.gmail.kurmazpavel.Discount;
import com.gmail.kurmazpavel.Order;
import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.dto.DiscountDTO;
import com.gmail.kurmazpavel.dto.OrderDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
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
