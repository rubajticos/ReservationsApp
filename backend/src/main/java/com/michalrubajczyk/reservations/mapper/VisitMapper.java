package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.VisitDTO;
import com.michalrubajczyk.reservations.entity.Visit;
import com.michalrubajczyk.reservations.types.VisitStatus;

public class VisitMapper extends BaseMapper<Visit, VisitDTO> {

    private PatientMapper patientMapper;
    private DoctorMapper doctorMapper;

    public VisitMapper(PatientMapper patientMapper, DoctorMapper doctorMapper) {
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public Visit dtoToEntity(VisitDTO dto) {
        Visit entity = new Visit();
        entity.setId(dto.getId());
        entity.setRegistrationDateTime(dto.getRegistationDateTime());
        entity.setDateTime(dto.getDateTime());
        entity.setStatus(VisitStatus.valueOf(dto.getStatus()));
        entity.addDoctor(doctorMapper.dtoToEntity(dto.getDoctor()));
        entity.addPatient(patientMapper.dtoToEntity(dto.getPatient()));

        return entity;
    }

    @Override
    public VisitDTO entityToDto(Visit entity) {
        VisitDTO dto = new VisitDTO();
        dto.setId(entity.getId());
        dto.setRegistationDateTime(entity.getRegistrationDateTime());
        dto.setDateTime(entity.getDateTime());
        dto.setStatus(entity.getStatus().toString());
        dto.setDoctor(doctorMapper.entityToDto(entity.getDoctor()));
        dto.setPatient(patientMapper.entityToDto(entity.getPatient()));

        return dto;
    }
}
