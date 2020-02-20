package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Long, Authority> {

    Authority findByName(String name);
}
