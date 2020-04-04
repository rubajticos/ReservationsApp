package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Specialization;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    Optional<Specialization> findByType(SpecializationType type);

}
