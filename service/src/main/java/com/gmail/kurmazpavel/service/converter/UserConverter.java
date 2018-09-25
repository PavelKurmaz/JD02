package com.gmail.kurmazpavel.service.converter;

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

@Component("userConverter")
public class UserConverter implements Converter<UserDTO, User> {
    @Autowired
    @Qualifier("addressConverter")
    private Converter<AddressDTO, Address> addressConverter;
    @Autowired
    @Qualifier("discountConverter")
    private Converter<DiscountDTO, Discount> discountConverter;
    @Autowired
    @Qualifier("orderConverter")
    private Converter<OrderDTO, Order> orderConverter;

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
