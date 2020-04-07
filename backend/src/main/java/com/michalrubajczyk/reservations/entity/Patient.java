package com.michalrubajczyk.reservations.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Setter(AccessLevel.NONE)
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Visit> visits = new HashSet<>();

    public Patient() {
    }

    public void addUser(User user) {
        this.user = user;
        user.setPatient(this);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + super.getUuid() + '\'' +
                ", uuid='" + super.getUuid() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                ", visits=" + Arrays.toString(visits.toArray()) +
                '}';
    }
}
