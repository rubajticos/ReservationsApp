package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.dto.RegistrationDTO;
import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;
import com.michalrubajczyk.reservations.entity.Authority;
import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.User;
import com.michalrubajczyk.reservations.mapper.PatientMapper;
import com.michalrubajczyk.reservations.mapper.RegisterUserMapper;
import com.michalrubajczyk.reservations.repository.AuthorityRepository;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import com.michalrubajczyk.reservations.types.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    private PatientRepository patientRepository;
    private AuthorityRepository authorityRepository;
    private PatientMapper patientMapper;
    private RegisterUserMapper registerUserMapper;
    private PasswordEncodingService passwordEncodingService;

    @Autowired
    public RegisterServiceImpl(PatientRepository patientRepository, AuthorityRepository authorityRepository, PasswordEncodingService passwordEncodingService) {
        this.patientRepository = patientRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncodingService = passwordEncodingService;
        patientMapper = new PatientMapper();
        registerUserMapper = new RegisterUserMapper();
    }

    @Override
    @Transactional
    public void register(RegistrationDTO registrationData) {
        UserCredentialsDTO userCredentials = registrationData.getUserCredentials();
        PatientDTO patientDTO = registrationData.getPatient();

        User user = registerUserMapper.dtoToEntity(userCredentials);
        Patient patient = patientMapper.dtoToEntity(patientDTO);

        Authority authority = authorityRepository.findByName(AuthorityType.ROLE_USER);
        user.addAuthority(authority);
        user.setPassword(passwordEncodingService.encodeBCrypt(user.getPassword()));
        user.setDateCreated(new Date());

        patient.addUser(user);
        patientRepository.save(patient);
    }
}
