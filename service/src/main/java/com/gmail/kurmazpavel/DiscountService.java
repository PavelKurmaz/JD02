package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    DiscountDTO read(Long entityID);
    DiscountDTO readByName(String name);
    DiscountDTO create(DiscountDTO discountDTO);
    void update(Long id, Long discountId);
    DiscountDTO delete(DiscountDTO discountDTO);
    void applyDiscount(Long id, Long discountId);
    List<DiscountDTO> getAll();
    List<CatalogDTO> getByDiscount(Integer discount);
}
