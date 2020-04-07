package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.VisitStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Visit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private LocalDateTime registrationDateTime;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @Setter(AccessLevel.NONE)
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
        return uuid.equals(visit.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
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
