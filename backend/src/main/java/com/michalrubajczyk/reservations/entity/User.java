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
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @NotEmpty
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Exclude
    private String username;

    @NotEmpty
    @EqualsAndHashCode.Exclude
    private String password;

    @EqualsAndHashCode.Exclude
    private Date dateCreated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
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
