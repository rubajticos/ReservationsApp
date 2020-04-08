package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.DoctorDTO;
import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.dto.SpecializationDTO;
import com.michalrubajczyk.reservations.dto.VisitDTO;
import com.michalrubajczyk.reservations.entity.*;
import com.michalrubajczyk.reservations.types.SpecializationType;
import com.michalrubajczyk.reservations.types.VisitStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class VisitMapperTest {

    private VisitMapper visitMapper;
    private PatientMapper patientMapper;
    private DoctorMapper doctorMapper;
    private LocalDateTime registerDateTime;
    private LocalDateTime visitDateTime;



    @BeforeEach
    void setUp() {
        patientMapper = mock(PatientMapper.class);
        doctorMapper = mock(DoctorMapper.class);
        visitMapper = new VisitMapper(patientMapper, doctorMapper);

        registerDateTime = LocalDateTime.now();
        visitDateTime = LocalDateTime.of(2020, Month.APRIL, 14, 16, 20);
        given(patientMapper.dtoToEntity(any())).willReturn(createPatient());
        given(patientMapper.entityToDto(any())).willReturn(createPatientDTO());
        given(doctorMapper.dtoToEntity(any())).willReturn(createDoctor());
        given(doctorMapper.entityToDto(any())).willReturn(createDoctorDTO());
    }

    @Test
    void entityToDTOShouldConvertVisitToVisitDTO() {
        Visit entity = createVisit();

        VisitDTO dto = visitMapper.entityToDto(entity);

        assertThat(dto.getId(), equalTo(entity.getId()));
        assertThat(dto.getRegistationDateTime(), equalTo(entity.getRegistrationDateTime()));
        assertThat(dto.getDateTime(), equalTo(entity.getDateTime()));
        assertThat(dto.getStatus(), equalTo(entity.getStatus().toString()));
        verify(doctorMapper).entityToDto(entity.getDoctor());
        verify(patientMapper).entityToDto(entity.getPatient());
    }

    @Test
    void dtoToEntityShouldConvertVisitDTOToVisit() {
        VisitDTO dto = createVisitDTO();

        Visit entity = visitMapper.dtoToEntity(dto);

        assertThat(entity.getId(), equalTo(dto.getId()));
        assertThat(entity.getRegistrationDateTime(), equalTo(dto.getRegistationDateTime()));
        assertThat(entity.getDateTime(), equalTo(dto.getDateTime()));
        assertThat(entity.getStatus(), equalTo(VisitStatus.valueOf(dto.getStatus())));
        verify(doctorMapper).dtoToEntity(dto.getDoctor());
        verify(patientMapper).dtoToEntity(dto.getPatient());
    }

    Visit createVisit() {
        Visit v = new Visit();
        v.setId(1L);
        v.setRegistrationDateTime(registerDateTime);
        v.setDateTime(visitDateTime);
        v.setStatus(VisitStatus.REGISTERED);
        v.addDoctor(createDoctor());
        v.addPatient(createPatient());

        return v;
    }

    VisitDTO createVisitDTO() {
        VisitDTO v = new VisitDTO();
        v.setId(1L);
        v.setRegistationDateTime(registerDateTime);
        v.setDateTime(visitDateTime);
        v.setStatus(VisitStatus.REGISTERED.toString());
        v.setDoctor(createDoctorDTO());
        v.setPatient(createPatientDTO());

        return v;
    }

    private Patient createPatient() {
        Patient p = new Patient();
        p.setId(1L);
        p.setFirstName("A");
        p.setLastName("B");
        p.setPhoneNumber("123456789");
        p.addUser(new User());

        return p;
    }

    private PatientDTO createPatientDTO() {
        PatientDTO p = new PatientDTO();
        p.setId(1L);
        p.setFirstName("A");
        p.setLastName("B");
        p.setPhoneNumber("123456789");

        return p;
    }

    private Doctor createDoctor() {
        Doctor d = new Doctor();
        d.setId(1L);
        d.setFirstName("A");
        d.setLastName("B");
        d.addSpecialization(new Specialization(SpecializationType.PEDIATRICIAN, SpecializationType.PEDIATRICIAN.getName()));

        return d;
    }

    private DoctorDTO createDoctorDTO() {
        DoctorDTO d = new DoctorDTO();
        d.setId(1L);
        d.setFirstName("A");
        d.setLastName("B");
        d.setSpecialization(new SpecializationDTO(1L, SpecializationType.PEDIATRICIAN.toString(), SpecializationType.PEDIATRICIAN.getName()));

        return d;
    }

}