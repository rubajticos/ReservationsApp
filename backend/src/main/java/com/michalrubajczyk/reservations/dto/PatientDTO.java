package com.michalrubajczyk.reservations.dto;

import lombok.Data;

@Data
public class PatientDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
