package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.AuthorityType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority(AuthorityType name) {
        this.name = name;
    }

    public Authority() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return id.equals(authority.id) &&
                name == authority.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
