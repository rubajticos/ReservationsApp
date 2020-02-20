package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.RegistrationDTO;

public interface RegisterService {

    boolean register(RegistrationDTO registrationData);
}
