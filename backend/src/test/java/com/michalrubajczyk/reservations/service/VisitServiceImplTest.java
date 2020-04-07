package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.VisitCreationDTO;
import com.michalrubajczyk.reservations.dto.VisitDTO;
import com.michalrubajczyk.reservations.entity.*;
import com.michalrubajczyk.reservations.mapper.DoctorMapper;
import com.michalrubajczyk.reservations.mapper.PatientMapper;
import com.michalrubajczyk.reservations.mapper.SpecializationMapper;
import com.michalrubajczyk.reservations.mapper.VisitMapper;
import com.michalrubajczyk.reservations.repository.DoctorRepository;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import com.michalrubajczyk.reservations.repository.VisitRepository;
import com.michalrubajczyk.reservations.types.SpecializationType;
import com.michalrubajczyk.reservations.types.VisitStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class VisitServiceImplTest {

    private VisitService visitService;
    private VisitRepository visitRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private VisitMapper visitMapper;
    private UserDetails userDetails;

    private Doctor doctor;
    private Patient patient;
    private LocalDateTime visitDate = LocalDateTime.of(2020, 1, 3, 8, 0);
    private LocalDateTime now = LocalDateTime.of(2020, 1, 1, 12, 0);
    private User user;

    @BeforeEach
    void setUp() {
        visitRepository = mock(VisitRepository.class);
        doctorRepository = mock(DoctorRepository.class);
        patientRepository = mock(PatientRepository.class);
        userDetails = mock(UserDetails.class);
        visitMapper = new VisitMapper(new PatientMapper(), new DoctorMapper(new SpecializationMapper()));
        visitService = new VisitServiceImpl(visitRepository, patientRepository, doctorRepository, visitMapper);

        user = new User();
        user.setUsername("USER");
        doctor = createDoctor();
        patient = createPatient();


        given(doctorRepository.findById(1L)).willReturn(Optional.of((doctor)));
        given(patientRepository.findById(1L)).willReturn(Optional.of(patient));
        given(userDetails.getUsername()).willReturn("USER");
    }

    @Test
    void createVisit_ShouldCreateAnEntityAndReturnsDTO() {
        CreatingVisitEntityModel model = prepareCreationData();
        given(visitRepository.save(ArgumentMatchers.any())).willReturn(model.visitEntity);

        VisitDTO result = visitService.createVisit(model.visitCreationDTO);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getDateTime(), equalTo(model.visitCreationDTO.getDateTime()));
        assertThat(result.getRegistationDateTime(), equalTo(model.visitCreationDTO.getRegistationDateTime()));
        assertThat(result.getStatus(), equalTo(VisitStatus.REGISTERED.toString()));
        assertThat(result.getDoctor().getId(), equalTo(model.visitCreationDTO.getDoctorId()));
        assertThat(result.getPatient().getId(), equalTo(model.visitCreationDTO.getPatientId()));
        verify(doctorRepository).findById(model.visitCreationDTO.getDoctorId());
        verify(patientRepository).findById(model.visitCreationDTO.getPatientId());
        verify(visitRepository).save(ArgumentMatchers.any(Visit.class));
    }

    @Test
    void createVisit_ShouldThrowExceptionWhenDoctorNotFound() {
        CreatingVisitEntityModel model = prepareCreationData();
        given(doctorRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> visitService.createVisit(model.visitCreationDTO));
    }

    @Test
    void createVisit_ShouldThrowExceptionWhenPatientNotFound() {
        CreatingVisitEntityModel model = prepareCreationData();
        given(patientRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> visitService.createVisit(model.visitCreationDTO));
    }

    @Test
    void createVisit_ShouldThrowExceptionWhenStatusHasWrongValue() {
        CreatingVisitEntityModel model = prepareCreationData();
        prepareCreationData().visitCreationDTO.setStatus("WRONG_STATUS");

        assertThrows(ResponseStatusException.class, () -> visitService.createVisit(model.visitCreationDTO));
    }

    @Test
    void createVisit_ShouldThrowExceptionWhenSaveIntoDBFailed() {
        CreatingVisitEntityModel model = prepareCreationData();
        given(visitRepository.save(any())).willThrow(ResponseStatusException.class);

        assertThrows(ResponseStatusException.class, () -> visitService.createVisit(model.visitCreationDTO));
    }

    @Test
    void getVisitsForPatient_ShouldReturnSetOfVisitsDTO() {
        Patient p = createPatient();
        Set<Visit> visits = prepareVisits();
        given(patientRepository.findById(p.getId())).willReturn(Optional.of(patient));
        given(visitRepository.findByPatient(patient)).willReturn(visits);

        Set<VisitDTO> result = visitService.getVisitsForPatient(p.getId(), userDetails);

        assertThat(result, hasSize(2));
    }

    @Test
    void getVisitsForPatient_ShouldReturnEmptySet() {
        Patient p = createPatient();
        given(patientRepository.findById(p.getId())).willReturn(Optional.of(patient));
        given(visitRepository.findByPatient(patient)).willReturn(new HashSet<>());

        Set<VisitDTO> result = visitService.getVisitsForPatient(p.getId(), userDetails);

        assertThat(result, hasSize(0));
    }

    @Test
    void getVisitsForPatient_ShouldThrowExceptionWhenUserNotPatientOwner() {
        Patient p = createPatient();
        given(patientRepository.findById(p.getId())).willReturn(Optional.of(patient));
        given(userDetails.getUsername()).willReturn("OTHER_USER");

        assertThrows(ResponseStatusException.class, () -> visitService.getVisitsForPatient(p.getId(), userDetails));
    }

    private Set<Visit> prepareVisits() {
        Patient p = createPatient();

        Visit v1 = new Visit();
        v1.setId(1L);
        v1.addDoctor(createDoctor());
        v1.addPatient(p);
        v1.setRegistrationDateTime(now);
        v1.setDateTime(visitDate);
        v1.setStatus(VisitStatus.CONFIRMED);

        Visit v2 = new Visit();
        v2.setId(2L);
        v2.addDoctor(createDoctor());
        v2.addPatient(p);
        v2.setRegistrationDateTime(now);
        v2.setDateTime(visitDate);
        v2.setStatus(VisitStatus.CONFIRMED);

        Set<Visit> visits = new HashSet<>();
        visits.add(v1);
        visits.add(v2);

        return visits;
    }

    private CreatingVisitEntityModel prepareCreationData() {
        CreatingVisitEntityModel model = new CreatingVisitEntityModel();
        model.visitCreationDTO = VisitCreationDTO.builder()
                .id(1L)
                .dateTime(visitDate)
                .doctorId(1L)
                .patientId(1L)
                .registationDateTime(now)
                .status(VisitStatus.NEW.toString())
                .build();

        model.visitEntity = new Visit();
        model.visitEntity.setId(1L);
        model.visitEntity.setDateTime(visitDate);
        model.visitEntity.addDoctor(doctor);
        model.visitEntity.addPatient(patient);
        model.visitEntity.setRegistrationDateTime(now);
        model.visitEntity.setStatus(VisitStatus.REGISTERED);

        return model;
    }

    private Doctor createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("A");
        doctor.setLastName("B");
        doctor.addSpecialization(new Specialization(SpecializationType.PEDIATRICIAN, SpecializationType.PEDIATRICIAN.getName()));
        return doctor;
    }

    private Patient createPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("AA");
        patient.setLastName("BB");
        patient.setEmail("a@b.com");
        patient.setPhoneNumber("123456789");
        patient.addUser(user);
        return patient;
    }

    private static class CreatingVisitEntityModel {
        public VisitCreationDTO visitCreationDTO;
        public Visit visitEntity;
    }
}