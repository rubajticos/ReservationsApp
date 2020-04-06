package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
