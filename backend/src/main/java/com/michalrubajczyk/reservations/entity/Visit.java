package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.VisitStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity {

    @EqualsAndHashCode.Exclude
    private LocalDateTime registrationDateTime;

    @EqualsAndHashCode.Exclude
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private VisitStatus status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
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
    public String toString() {
        return "Visit{" +
                "id=" + super.getId() +
                ", uuid=" + super.getUuid() +
                ", registrationDateTime=" + registrationDateTime +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", patient=" + patient.getId() +
                ", doctor=" + doctor.getId() +
                '}';
    }
}
