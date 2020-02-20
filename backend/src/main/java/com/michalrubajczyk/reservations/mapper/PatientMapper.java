package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.entity.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper implements Mapper<Patient, PatientDTO> {

    @Override
    public Patient dtoToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setFirstName(dto.getUserName());
        patient.setLastName(dto.getLastName());
        patient.setPhoneNumber(dto.getPhoneNumber());

        return patient;
    }

    @Override
    public List<Patient> dtoListToEntityList(List<PatientDTO> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public PatientDTO entityToDto(Patient entity) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(entity.getId());
        patientDTO.setUserName(entity.getFirstName());
        patientDTO.setLastName(entity.getLastName());
        patientDTO.setPhoneNumber(entity.getPhoneNumber());

        return patientDTO;
    }

    @Override
    public List<PatientDTO> entityListToDtoList(List<Patient> entityList) {
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
