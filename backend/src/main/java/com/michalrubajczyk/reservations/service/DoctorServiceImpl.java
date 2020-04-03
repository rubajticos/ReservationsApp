package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.repository.DoctorRepository;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DoctorServiceImpl implements DoctorService {

    DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> getDoctorsByName(String firstName, String lastName) {
        return doctorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(SpecializationType specializationType) {
        return doctorRepository.findBySpecialization(specializationType);
    }
}
