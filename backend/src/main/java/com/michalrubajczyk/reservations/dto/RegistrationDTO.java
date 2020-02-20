package com.michalrubajczyk.reservations.dto;

import lombok.Data;

@Data
public class RegistrationDTO {

    private UserCredentialsDTO userCredentialsDTO;
    private PatientDTO patientDTO;
}
