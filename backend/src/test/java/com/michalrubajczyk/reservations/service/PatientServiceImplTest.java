package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.User;
import com.michalrubajczyk.reservations.mapper.PatientMapper;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import com.michalrubajczyk.reservations.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatientServiceImplTest {

    UserRepository userRepository;
    PatientRepository patientRepository;
    PatientMapper patientMapper;
    User user;
    PatientServiceImpl service;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        patientRepository = mock(PatientRepository.class);
        patientMapper = new PatientMapper();
        user = prepareUser();
        service = new PatientServiceImpl(patientRepository, userRepository, patientMapper);
    }

    @Test
    public void getPatientByUsernameShouldThrowExceptionWhenPatientNotFound() {
        given(patientRepository.findByUser(user)).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.getPatientByUsername("USER"));
    }

    @Test
    public void getPatientByUsernameShouldThrowExceptionWhenUserNotFound() {
        given(userRepository.findByUsername("USER")).willReturn(null);

        assertThrows(ResponseStatusException.class, () -> service.getPatientByUsername("USER"));
    }

    @Test
    public void getPatientByUsernameShouldReturnsUserForPatient() {
        Patient p = preparePatient();
        p.addUser(user);
        given(userRepository.findByUsername("USER")).willReturn(user);
        given(patientRepository.findByUser(user)).willReturn(Optional.of(p));
        PatientDTO patientDTO = patientMapper.entityToDto(p);

        PatientDTO result = service.getPatientByUsername("USER");

        assertThat(result, equalTo(patientDTO));
    }

    private User prepareUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("USER");

        return user;
    }

    private Patient preparePatient() {
        Patient p = new Patient();
        p.setId(1L);
        p.setFirstName("P");
        p.setLastName("S ");
        p.setEmail("p@s.com");
        p.setPhoneNumber("123456789");

        return p;
    }
}