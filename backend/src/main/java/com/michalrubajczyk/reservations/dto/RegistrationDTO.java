package com.michalrubajczyk.reservations.dto;

import lombok.Data;

import javax.validation.Valid;

@Data
public class RegistrationDTO {

    @Valid
    private UserCredentialsDTO userCredentialsDTO;
    @Valid
    private PatientDTO patientDTO;
}
