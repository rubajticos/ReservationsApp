package com.michalrubajczyk.reservations.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    public Doctor() {
    }

    public void addSpecialization(Specialization specialization) {
        this.specialization = specialization;
        this.specialization.getDoctors().add(this);
    }

}
