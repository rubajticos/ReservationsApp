package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.SpecializationType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class VisitTest {

    @Test
    void addPatientShouldCreateRelationBetweenVisitAndPatient() {
        Patient patient = CreatePatient();
        Visit visit = new Visit();

        visit.addPatient(patient);

        assertThat(visit.getPatient(), equalTo(patient));
        assertThat(visit.getPatient(), is(patient));
        assertThat(patient.getVisits(), contains(visit));
    }

    @Test
    void addDoctorShouldCreateRelationBetweenVisitAndDoctor() {
        Doctor doctor = CreateDoctor();
        Visit visit = new Visit();

        visit.addDoctor(doctor);

        assertThat(visit.getDoctor(), equalTo(doctor));
        assertThat(visit.getDoctor(), is(doctor));
        assertThat(doctor.getVisits(), contains(visit));
    }

    private Patient CreatePatient() {
        Patient p = new Patient();
        p.setId(1L);
        p.setFirstName("A");
        p.setLastName("B");
        p.setPhoneNumber("123456789");
        p.setUser(new User());

        return p;
    }

    private Doctor CreateDoctor() {
        Doctor d = new Doctor();
        d.setId(1L);
        d.setFirstName("A");
        d.setLastName("B");
        d.setSpecialization(new Specialization(SpecializationType.PEDIATRICIAN, SpecializationType.PEDIATRICIAN.getName()));

        return d;
    }
}

