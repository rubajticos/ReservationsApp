package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.dto.RegistrationDTO;
import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;
import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.User;
import com.michalrubajczyk.reservations.mapper.Mapper;
import com.michalrubajczyk.reservations.mapper.PatientMapper;
import com.michalrubajczyk.reservations.mapper.RegisterUserMapper;
import com.michalrubajczyk.reservations.repository.AuthorityRepository;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    private PatientRepository patientRepository;
    private AuthorityRepository authorityRepository;
    private PatientMapper patientMapper;
    private RegisterUserMapper registerUserMapper;

    @Autowired
    public RegisterServiceImpl(PatientRepository patientRepository, AuthorityRepository authorityRepository) {
        this.patientRepository = patientRepository;
        this.authorityRepository = authorityRepository;
        patientMapper = new PatientMapper();
        registerUserMapper = new RegisterUserMapper();
    }

    @Override
    @Transactional
    public boolean register(RegistrationDTO registrationData) {
        UserCredentialsDTO userCredentials = registrationData.getUserCredentialsDTO();
        PatientDTO patientDTO = registrationData.getPatientDTO();

        User user = registerUserMapper.dtoToEntity(userCredentials);
        Patient patient = patientMapper.dtoToEntity(patientDTO);

        

    }
}
