package com.michalrubajczyk.reservations.entity;

import com.michalrubajczyk.reservations.types.AuthorityType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public Authority(AuthorityType name) {
        this.name = name;
    }
}
