package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Authority;
import com.michalrubajczyk.reservations.types.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(AuthorityType authorityType);
}
