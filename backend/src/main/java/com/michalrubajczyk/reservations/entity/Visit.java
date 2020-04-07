package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.VisitStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
public class Visit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime registrationDateTime;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public Visit() {
    }

    public void addPatient(Patient patient) {
        this.patient = patient;
        this.patient.getVisits().add(this);
    }

    public void addDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.doctor.getVisits().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) &&
                Objects.equals(registrationDateTime, visit.registrationDateTime) &&
                Objects.equals(dateTime, visit.dateTime) &&
                status == visit.status &&
                Objects.equals(patient, visit.patient) &&
                Objects.equals(doctor, visit.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationDateTime, dateTime, status, patient, doctor);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", registrationDateTime=" + registrationDateTime +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
