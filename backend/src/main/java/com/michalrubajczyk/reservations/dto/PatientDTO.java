package com.michalrubajczyk.reservations.dto;

import lombok.Data;

@Data
public class PatientDTO {

    private Long id;

    private String userName;

    private String lastName;

    private String phoneNumber;
}
