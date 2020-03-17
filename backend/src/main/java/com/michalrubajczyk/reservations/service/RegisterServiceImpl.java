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
import com.michalrubajczyk.reservations.repository.UserRepository;
import com.michalrubajczyk.reservations.types.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    private PatientRepository patientRepository;
    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PatientMapper patientMapper;
    private RegisterUserMapper registerUserMapper;
    private PasswordEncodingService passwordEncodingService;

    @Autowired
    public RegisterServiceImpl(PatientRepository patientRepository, AuthorityRepository authorityRepository, UserRepository userRepository, PasswordEncodingService passwordEncodingService) {
        this.patientRepository = patientRepository;
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.passwordEncodingService = passwordEncodingService;
        patientMapper = new PatientMapper();
        registerUserMapper = new RegisterUserMapper();
    }

    @Override
    @Transactional
    public void register(RegistrationDTO registrationData) {
        UserCredentialsDTO userCredentials = registrationData.getUserCredentials();
        PatientDTO patientDTO = registrationData.getPatient();

        checkUsernameAndEmailExists(userCredentials.getUsername(), patientDTO.getEmail());

        User user = registerUserMapper.dtoToEntity(userCredentials);
        Patient patient = patientMapper.dtoToEntity(patientDTO);

        Authority authority = authorityRepository.findByName(AuthorityType.ROLE_USER);
        user.addAuthority(authority);
        user.setPassword(passwordEncodingService.encodeBCrypt(user.getPassword()));
        user.setDateCreated(new Date());

        patient.addUser(user);
        patientRepository.save(patient);
    }

    private void checkUsernameAndEmailExists(String username, String email) {
        if (userRepository.countByUsername(username) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already used.");

        if (patientRepository.countByEmail(email) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail already used.");
    }
}
