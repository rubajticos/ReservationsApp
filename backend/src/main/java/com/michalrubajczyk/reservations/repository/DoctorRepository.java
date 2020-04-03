package com.michalrubajczyk.reservations.repository;

import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByFirstNameAndLastName(String firstName, String lastName);

    List<Doctor> findBySpecialization(SpecializationType specializationType);
}
