package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.AuthorityType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Authority extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private AuthorityType name;

    @ManyToMany(mappedBy = "authorities")
    @EqualsAndHashCode.Exclude
    private Set<User> users;

    public Authority(AuthorityType name) {
        this.name = name;
    }

    public Authority() {
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + super.getId() +
                ", uuid=" + super.getUuid() +
                ", name=" + name +
                '}';
    }
}
