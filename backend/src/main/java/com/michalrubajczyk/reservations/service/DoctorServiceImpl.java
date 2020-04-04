package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.repository.DoctorRepository;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public List<Doctor> getDoctorsBySpecialization(String specializationType) {
        SpecializationType specialization = SpecializationType.fromName(specializationType);
        return doctorRepository.findBySpecialization(specialization);
    }
}
