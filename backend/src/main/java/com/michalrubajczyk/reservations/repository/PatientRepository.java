package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    int countByEmail(String email);

    Optional<Patient> findByUser(User user);
}
