package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Set<Visit> findByPatient(Patient patient);

    boolean existsByDateTimeAndDoctor(LocalDateTime dateTime, Doctor doctor);
}
