package com.michalrubajczyk.reservations.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper<E, D> {

    public abstract E dtoToEntity(D dto);

    public abstract D entityToDto(E entity);

    public List<E> dtoListToEntityList(List<D> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public List<D> entityListToDtoList(List<E> entityList) {
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
