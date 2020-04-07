package com.michalrubajczyk.reservations.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends BaseEntity {

    @EqualsAndHashCode.Exclude
    private String firstName;

    @EqualsAndHashCode.Exclude
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String phoneNumber;

    @EqualsAndHashCode.Exclude
    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
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
                ", user=" + user.toString() +
                '}';
    }
}
