package com.michalrubajczyk.reservations.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.entity.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientMapperTest {
    private static PatientMapper patientMapper;
    private Patient patient;
    private PatientDTO patientDTO;

    @BeforeAll
    static void setUp() {
        patientMapper = new PatientMapper();
    }

    @AfterEach
    void tearDown() {
        this.patient = null;
        this.patientDTO = null;
    }

    @Test
    void patientShouldBeConvertedToPatientDTO() {
        generatePatient();

        this.patientDTO = patientMapper.entityToDto(this.patient);

        assertPatientDTO();
    }

    private void assertPatientDTO() {
        assertEquals(patientDTO.getId(), patient.getId());
        assertEquals(patientDTO.getUserName(), patient.getFirstName());
        assertEquals(patientDTO.getLastName(), patient.getLastName());
        assertEquals(patientDTO.getPhoneNumber(), patient.getPhoneNumber());
    }

    @Test
    void patientDTOShouldBeConvertedToPatient() {
        generatePatientDTO();

        this.patient = patientMapper.dtoToEntity(this.patientDTO);

        assertPatient();
    }

    private void assertPatient() {
        assertEquals(patient.getId(), patientDTO.getId());
        assertEquals(patient.getFirstName(), patientDTO.getUserName());
        assertEquals(patient.getLastName(), patientDTO.getLastName());
        assertEquals(patient.getPhoneNumber(), patientDTO.getPhoneNumber());
    }

    private void generatePatient() {
        this.patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("a");
        patient.setLastName("b");
        patient.setPhoneNumber("123456789");
    }

    private void generatePatientDTO() {
        this.patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setUserName("a");
        patientDTO.setLastName("b");
        patientDTO.setPhoneNumber("123456789");
    }


}