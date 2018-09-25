package com.gmail.kurmazpavel.service.DTOConverter;

import com.gmail.kurmazpavel.Discount;
import com.gmail.kurmazpavel.dto.DiscountDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("discountDTOConverter")
public class DiscountDTOConverter implements DTOConverter<DiscountDTO, Discount> {

    @Override
    public DiscountDTO toDTO(Discount entity) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(entity.getId());
        discountDTO.setLast(entity.getLast());
        discountDTO.setName(entity.getName());
        discountDTO.setPercent(entity.getPercent());
        return discountDTO;
    }

    @Override
    public List<DiscountDTO> toDTOList(List<Discount> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
