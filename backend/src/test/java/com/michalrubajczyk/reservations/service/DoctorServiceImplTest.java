package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.repository.DoctorRepository;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DoctorServiceImplTest {

    private DoctorRepository doctorRepository;
    private List<Doctor> doctors = new ArrayList<>();
    private DoctorService service;

    @BeforeEach
    void setUp() {
        doctorRepository = mock(DoctorRepository.class);
        service = new DoctorServiceImpl(doctorRepository);
        prepareDoctors();
    }

    @Test
    void getAllDoctorsShouldReturnList() {
        given(doctorRepository.findAll()).willReturn(doctors);

        List<Doctor> result = service.getAllDoctors();

        assertThat(result.size(), equalTo(5));
        assertThat(result, equalTo(doctors));
    }

    @Test
    void getDoctorByIdShouldReturnObject() {
        Doctor doc = doctors.get(0);
        given(doctorRepository.findById(1L)).willReturn(Optional.of(doc));

        Optional<Doctor> result = service.getDoctorById(1L);

        assertThat(result.get(), equalTo(doc));
        assertThat(result.get().hashCode(), equalTo(doc.hashCode()));
        assertThat(result.get().getId(), equalTo(doc.getId()));
    }

    @Test
    void getDoctorsByNameShouldReturnListOfDoctors() {
        Doctor doc1 = CreateDoctor(1L, "FIRST", "LAST", SpecializationType.PEDIATRICIAN);
        Doctor doc2 = CreateDoctor(2L, "FIRST", "LAST", SpecializationType.SUREGON);
        given(doctorRepository.findByFirstNameAndLastName("FIRST", "LAST")).willReturn(Arrays.asList(doc1, doc2));

        List<Doctor> result = service.getDoctorsByName("FIRST", "LAST");

        assertThat(result, hasSize(2));
        assertThat(result, contains(doc1, doc2));
    }


    @Test
    void getDoctorBySpecializationShouldThrowExceptionWhenSpecializationInvalid() {
        String invalidSpecialization = "SPEC";

        assertThrows(IllegalArgumentException.class, () -> service.getDoctorsBySpecialization(invalidSpecialization));
    }

    @Test
    void getDoctorsBySpecializationShouldReturnListOfPediatricians() {
        given(doctorRepository.findBySpecialization(SpecializationType.PEDIATRICIAN)).willReturn(Arrays.asList(doctors.get(0), doctors.get(1)));

        List<Doctor> result = service.getDoctorsBySpecialization("Pediatrician");

        assertThat(result, hasSize(2));
        assertThat(result, contains(doctors.get(0), doctors.get(1)));
    }

    @Test
    void getDoctorsBySpecializationShouldReturnListOfSurgeons() {
        given(doctorRepository.findBySpecialization(SpecializationType.SUREGON)).willReturn(Arrays.asList(doctors.get(2), doctors.get(3), doctors.get(4)));

        List<Doctor> result = service.getDoctorsBySpecialization("Surgeon");

        assertThat(result, hasSize(3));
        assertThat(result, contains(doctors.get(2), doctors.get(3), doctors.get(4)));
    }

    private void prepareDoctors() {
        Doctor doc1 = CreateDoctor(1L, "First1", "Last1", SpecializationType.PEDIATRICIAN);
        Doctor doc2 = CreateDoctor(2L, "First2", "Last2", SpecializationType.PEDIATRICIAN);
        Doctor doc3 = CreateDoctor(3L, "First3", "Last3", SpecializationType.SUREGON);
        Doctor doc4 = CreateDoctor(4L, "First4", "Last4", SpecializationType.SUREGON);
        Doctor doc5 = CreateDoctor(5L, "First4", "Last4", SpecializationType.SUREGON);

        this.doctors.add(doc1);
        this.doctors.add(doc2);
        this.doctors.add(doc3);
        this.doctors.add(doc4);
        this.doctors.add(doc5);
    }

    private Doctor CreateDoctor(Long id, String firstName, String lastName, SpecializationType specialization) {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setSpecialization(specialization);

        return doctor;
    }
}