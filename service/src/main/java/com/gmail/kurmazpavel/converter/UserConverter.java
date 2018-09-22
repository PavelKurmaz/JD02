package com.gmail.kurmazpavel.converter;

import com.gmail.kurmazpavel.User;
import com.gmail.kurmazpavel.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserConverter implements Converter<UserDTO, User> {
    private AddressConverter addressConverter = new AddressConverter();
    private DiscountConverter discountConverter = new DiscountConverter();
    private OrderConverter orderConverter = new OrderConverter();

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setDisabled(dto.isDisabled());
        user.setAddress(addressConverter.toEntity(dto.getAddress()));
        user.setRoleId(dto.getRoleId());
        if (dto.getDiscount() != null)
            user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        user.setOrders(orderConverter.toEntityList(dto.getOrders()));
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
