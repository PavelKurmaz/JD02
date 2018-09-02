package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.dto.ShippingListDTO;
import java.util.List;

public interface ListService {
    ShippingListDTO read(Long entityID);
    ShippingListDTO create(ShippingListDTO shippingListDTO);
    ShippingListDTO update(ShippingListDTO shippingListDTO);
    ShippingListDTO delete (ShippingListDTO shippingListDTO);
    List<ShippingListDTO> getAll();
}
