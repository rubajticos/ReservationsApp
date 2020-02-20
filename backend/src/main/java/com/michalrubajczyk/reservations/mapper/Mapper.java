package com.michalrubajczyk.reservations.mapper;

import java.util.List;

public interface Mapper<E, D> {

    E dtoToEntity(D dto);

    List<E> dtoListToEntityList(List<D> dtoList);

    D entityToDto(E entity);

    List<D> entityListToDtoList(List<E> entityList);

}
