package com.michalrubajczyk.reservations.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.entity.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientMapperTest {
    private static PatientMapper patientMapper;

    @BeforeAll
    static void setUp() {
        patientMapper = new PatientMapper();
    }

    @Test
    void patientShouldBeConvertedToPatientDTO() {
        Patient patient = generatePatient();

        PatientDTO patientDTO = patientMapper.entityToDto(patient);

        assertPatientDTO(patientDTO, patient);
    }

    @Test
    void patientsListShouldBeConvertedToPatientsDTOList() {
        List<Patient> patients = generatePatientList();

        List<PatientDTO> patientDTOList = patientMapper.entityListToDtoList(patients);

        for (int i = 0; i < patientDTOList.size(); i++) {
            assertPatientDTO(patientDTOList.get(i), patients.get(i));
        }
    }

    private void assertPatientDTO(PatientDTO patientDTO, Patient patient) {
        assertEquals(patientDTO.getId(), patient.getId());
        assertEquals(patientDTO.getUserName(), patient.getFirstName());
        assertEquals(patientDTO.getLastName(), patient.getLastName());
        assertEquals(patientDTO.getPhoneNumber(), patient.getPhoneNumber());
    }

    @Test
    void patientDTOShouldBeConvertedToPatient() {
        PatientDTO patientDTO = generatePatientDTO();

        Patient patient = patientMapper.dtoToEntity(patientDTO);

        assertPatient(patient, patientDTO);
    }

    @Test
    void patientsDTOListShouldBeConvertedToPatientsList() {
        List<PatientDTO> patientsDTO = generatePatientDTOList();

        List<Patient> patients = patientMapper.dtoListToEntityList(patientsDTO);

        for (int i = 0; i < patients.size(); i++) {
            assertPatient(patients.get(i), patientsDTO.get(i));
        }
    }

    private void assertPatient(Patient patient, PatientDTO patientDTO) {
        assertEquals(patient.getId(), patientDTO.getId());
        assertEquals(patient.getFirstName(), patientDTO.getUserName());
        assertEquals(patient.getLastName(), patientDTO.getLastName());
        assertEquals(patient.getPhoneNumber(), patientDTO.getPhoneNumber());
    }

    private Patient generatePatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("a");
        patient.setLastName("b");
        patient.setPhoneNumber("123456789");

        return patient;
    }

    private PatientDTO generatePatientDTO() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setUserName("a");
        patientDTO.setLastName("b");
        patientDTO.setPhoneNumber("123456789");

        return patientDTO;
    }

    private List<Patient> generatePatientList() {
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Patient p = new Patient();
            p.setId((long) i);
            p.setFirstName("a");
            p.setLastName("b");
            p.setPhoneNumber("123456789");
            patients.add(p);
        }

        return patients;
    }

    private List<PatientDTO> generatePatientDTOList() {
        List<PatientDTO> patientsDTO = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PatientDTO p = new PatientDTO();
            p.setId((long) i);
            p.setUserName("a");
            p.setLastName("b");
            p.setPhoneNumber("123456789");
            patientsDTO.add(p);
        }

        return patientsDTO;
    }

}