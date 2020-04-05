package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.DoctorDTO;
import com.michalrubajczyk.reservations.entity.Doctor;

public class DoctorMapper extends BaseMapper<Doctor, DoctorDTO> {

    private SpecializationMapper specializationMapper;

    public DoctorMapper(SpecializationMapper specializationMapper) {
        this.specializationMapper = specializationMapper;
    }

    @Override
    public Doctor dtoToEntity(DoctorDTO dto) {
        Doctor entity = new Doctor();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.addSpecialization(specializationMapper.dtoToEntity(dto.getSpecialization()));

        return entity;
    }

    @Override
    public DoctorDTO entityToDto(Doctor entity) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setSpecialization(specializationMapper.entityToDto(entity.getSpecialization()));

        return dto;
    }
}
