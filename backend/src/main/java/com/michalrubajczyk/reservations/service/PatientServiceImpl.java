package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.User;
import com.michalrubajczyk.reservations.mapper.PatientMapper;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import com.michalrubajczyk.reservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;
    UserRepository userRepository;
    PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    @Transactional
    public PatientDTO getPatientByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + user + " not found." );

        Optional<Patient> optionalPatient = patientRepository.findByUser(user);
        if (optionalPatient.isPresent()) {
            return patientMapper.entityToDto(optionalPatient.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient for " + user + " not found." );
        }
    }
}
