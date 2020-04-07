package com.michalrubajczyk.reservations.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Doctor extends BaseEntity {

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @Setter(AccessLevel.NONE)
    private Specialization specialization;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Visit> visits = new HashSet<>();

    public Doctor() {
    }

    public void addSpecialization(Specialization specialization) {
        this.specialization = specialization;
        this.specialization.getDoctors().add(this);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + super.getId() + '\'' +
                ", uuid='" + super.getUuid() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialization=" + specialization +
                ", visits=" + visits +
                '}';
    }
}
