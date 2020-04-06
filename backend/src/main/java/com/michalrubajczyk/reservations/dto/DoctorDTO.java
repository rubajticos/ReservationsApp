package com.michalrubajczyk.reservations.dto;

import lombok.Data;

@Data
public class DoctorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private SpecializationDTO specialization;

    public DoctorDTO() {
    }
}
