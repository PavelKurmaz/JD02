package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.*;
import com.gmail.kurmazpavel.dto.*;
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
    @Autowired
    @Qualifier("newsConverter")
    private Converter<NewsDTO, News> newsConverter;

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
        user.setNews(newsConverter.toEntityList(dto.getNews()));
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
