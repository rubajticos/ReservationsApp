package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;

public interface RegisterService {

    boolean register(UserCredentialsDTO userCredentialsDTO, PatientDTO patientDTO );

}
