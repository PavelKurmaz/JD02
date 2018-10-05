package com.gmail.kurmazpavel.service.DTOConverter;

import java.util.List;

public interface DTOConverter<D, E> {
    D toDTO(E entity);
    List<D> toDTOList(List<E> list);
}