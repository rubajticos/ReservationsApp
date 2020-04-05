package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.entity.Specialization;
import com.michalrubajczyk.reservations.repository.DoctorRepository;
import com.michalrubajczyk.reservations.repository.SpecializationRepository;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    DoctorRepository doctorRepository;
    SpecializationRepository specializationRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecializationRepository specializationRepository) {
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
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
        SpecializationType specType = SpecializationType.valueOf(specializationType);
        Optional<Specialization> optionalSpecialization = specializationRepository.findByType(specType);
        List<Doctor> doctors = null;
        if(optionalSpecialization.isPresent()) {
            doctors = doctorRepository.findBySpecialization(optionalSpecialization.get());
        }

        return doctors;
    }
}
