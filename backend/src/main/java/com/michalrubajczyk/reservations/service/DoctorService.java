package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<Doctor> getAllDoctors();

    Optional<Doctor> getDoctorById(Long id);

    List<Doctor> getDoctorsByName(String name, String lastName);

    List<Doctor> getDoctorsBySpecialization(String specializationType);

}
