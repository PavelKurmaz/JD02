package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.DiscountDTO;

import java.util.List;

public interface CatalogService {
    CatalogDTO read(Long entityID);
    CatalogDTO create(CatalogDTO catalogDTO);
    CatalogDTO update(CatalogDTO catalogDTO);
    CatalogDTO delete (CatalogDTO catalogDTO);
    List<CatalogDTO> getAll();
    Long count (Double min, Double max);
    List<CatalogDTO> getByPrice(Double min, Double max);
    List<DiscountDTO> getDiscounts(Long id);
}
