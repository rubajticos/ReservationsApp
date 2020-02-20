package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Long, Patient> {
}
