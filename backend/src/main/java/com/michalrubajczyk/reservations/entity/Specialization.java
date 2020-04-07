package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.SpecializationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Specialization extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private SpecializationType type;

    @EqualsAndHashCode.Exclude
    private String name;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
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
                "id=" + super.getId() +
                ", uuid=" + super.getUuid() +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
