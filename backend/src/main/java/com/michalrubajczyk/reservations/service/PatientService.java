package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface PatientService {

    PatientDTO getPatientByUsername(String username);

}
