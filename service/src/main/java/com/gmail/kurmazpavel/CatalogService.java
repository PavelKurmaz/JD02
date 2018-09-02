package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import java.util.List;

public interface CatalogService {
    CatalogDTO read(Long entityID);
    CatalogDTO create(CatalogDTO catalogDTO);
    CatalogDTO update(CatalogDTO catalogDTO);
    CatalogDTO delete (CatalogDTO catalogDTO);
    List<CatalogDTO> getAll();
}
