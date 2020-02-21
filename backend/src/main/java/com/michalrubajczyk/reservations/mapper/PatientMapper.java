package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.entity.Patient;

public class PatientMapper extends BaseMapper<Patient, PatientDTO> {

    @Override
    public Patient dtoToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setPhoneNumber(dto.getPhoneNumber());

        return patient;
    }

    @Override
    public PatientDTO entityToDto(Patient entity) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(entity.getId());
        patientDTO.setFirstName(entity.getFirstName());
        patientDTO.setLastName(entity.getLastName());
        patientDTO.setPhoneNumber(entity.getPhoneNumber());

        return patientDTO;
    }
}
