package com.michalrubajczyk.reservations.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Visit> visits = new HashSet<>();

    public Doctor() {
    }

    public void addSpecialization(Specialization specialization) {
        this.specialization = specialization;
        this.specialization.getDoctors().add(this);
    }

}
