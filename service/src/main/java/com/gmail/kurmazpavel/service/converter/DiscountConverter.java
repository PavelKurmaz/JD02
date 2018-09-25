package com.gmail.kurmazpavel.service.converter;

import com.gmail.kurmazpavel.Discount;
import com.gmail.kurmazpavel.dto.DiscountDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("discountConverter")
public class DiscountConverter implements Converter<DiscountDTO, Discount>{

    @Override
    public Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setLast(dto.getLast());
        discount.setName(dto.getName());
        discount.setPercent(dto.getPercent());
        return discount;
    }

    @Override
    public List<Discount> toEntityList(List<DiscountDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
