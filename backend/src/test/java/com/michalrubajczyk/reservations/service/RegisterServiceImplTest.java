package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.dto.RegistrationDTO;
import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;
import com.michalrubajczyk.reservations.entity.Authority;
import com.michalrubajczyk.reservations.repository.AuthorityRepository;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import com.michalrubajczyk.reservations.repository.UserRepository;
import com.michalrubajczyk.reservations.types.AuthorityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RegisterServiceImplTest {
    private RegisterServiceImpl registerService;
    private PatientRepository patientRepository;
    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncodingService passwordEncodingService;

    @BeforeEach
    void setUp() {
        patientRepository = mock(PatientRepository.class);
        authorityRepository = mock(AuthorityRepository.class);
        userRepository = mock(UserRepository.class);
        passwordEncodingService = mock(PasswordEncodingService.class);

        registerService = new RegisterServiceImpl(patientRepository, authorityRepository, userRepository, passwordEncodingService);
    }

    @Test
    void registerShouldAddUserAndPatient() {
        //given
        RegistrationDTO registrationDTO = prepareRegistrationDTO();
        Authority authority = prepareUserAuthority();
        given(authorityRepository.findByName(AuthorityType.ROLE_USER)).willReturn(authority);

        //when
        registerService.register(registrationDTO);

        //then
        verify(authorityRepository).findByName(AuthorityType.ROLE_USER);
        verify(passwordEncodingService).encodeBCrypt(registrationDTO.getUserCredentials().getPassword());
        verify(patientRepository).save(any());
    }

    private Authority prepareUserAuthority() {
        Authority authority = new Authority();
        authority.setId(1L);
        authority.setName(AuthorityType.ROLE_USER);
        authority.setUsers(new HashSet<>());
        return authority;
    }

    private RegistrationDTO prepareRegistrationDTO() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername("a");
        userCredentialsDTO.setPassword("pass");

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("a");
        patientDTO.setLastName("b");
        patientDTO.setPhoneNumber("123456789");
        patientDTO.setEmail("c@cc.com");

        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setUserCredentials(userCredentialsDTO);
        registrationDTO.setPatient(patientDTO);

        return registrationDTO;
    }

    @Test
    void registerWithUsernameAlreadyUsedShouldThrowException() {
        //given
        RegistrationDTO registrationDTO = prepareRegistrationDTO();
        given(userRepository.countByUsername(anyString())).willReturn(1);

        //then
        assertThrows(ResponseStatusException.class, () -> registerService.register(registrationDTO));
    }

    @Test
    void registerWithEmailAlreadyUsedShouldThrowException() {
        //given
        RegistrationDTO registrationDTO = prepareRegistrationDTO();
        given(patientRepository.countByEmail(anyString())).willReturn(1);

        //then
        assertThrows(ResponseStatusException.class, () -> registerService.register(registrationDTO));
    }

}