package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.SpecializationDTO;
import com.michalrubajczyk.reservations.entity.Specialization;
import com.michalrubajczyk.reservations.types.SpecializationType;

public class SpecializationMapper extends BaseMapper<Specialization, SpecializationDTO> {

    @Override
    Specialization dtoToEntity(SpecializationDTO dto) {
        Specialization entity = new Specialization();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(SpecializationType.valueOf(dto.getType()));

        return entity;
    }

    @Override
    SpecializationDTO entityToDto(Specialization entity) {
        SpecializationDTO dto = new SpecializationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType().toString());

        return dto;
    }
}
