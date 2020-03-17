package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    int countByEmail(String email);
}
