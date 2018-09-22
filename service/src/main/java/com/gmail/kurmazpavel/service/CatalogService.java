package com.gmail.kurmazpavel.service;

import com.gmail.kurmazpavel.dto.CatalogDTO;
import com.gmail.kurmazpavel.dto.DiscountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CatalogService {
    CatalogDTO read(Long entityID);
    CatalogDTO create(CatalogDTO catalogDTO);
    CatalogDTO update(CatalogDTO catalogDTO);
    CatalogDTO delete (CatalogDTO catalogDTO);
    List<CatalogDTO> getAll();
    Long count (BigDecimal min, BigDecimal max);
    List<CatalogDTO> getByPrice(BigDecimal min, BigDecimal max);
    List<DiscountDTO> getDiscounts(Long id);
}
