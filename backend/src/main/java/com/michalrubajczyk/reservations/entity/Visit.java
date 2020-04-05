package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.VisitStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Patient patient;
    private Doctor doctor;

    public Visit() {
    }

}
