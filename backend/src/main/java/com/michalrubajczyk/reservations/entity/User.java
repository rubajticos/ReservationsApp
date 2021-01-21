package com.michalrubajczyk.reservations.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"username", "password", "dateCreated", "authorities", "patient"})
@Table(name = "users")
public class User extends BaseEntity {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    private String password;

    private Date dateCreated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Patient patient;

    public void addAuthority(Authority authority) {
        authorities.add(authority);
        authority.getUsers().add(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + '\'' +
                ", uuid='" + super.getUuid() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", authorities=" + Arrays.toString(authorities.toArray()) +
                '}';
    }
}
