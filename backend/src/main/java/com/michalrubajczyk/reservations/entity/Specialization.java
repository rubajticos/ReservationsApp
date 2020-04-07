package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.SpecializationType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Specialization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SpecializationType type;

    private String name;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    public Specialization(SpecializationType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Specialization() {
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
