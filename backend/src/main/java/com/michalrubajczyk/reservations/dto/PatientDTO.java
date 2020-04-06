package com.michalrubajczyk.reservations.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class PatientDTO {

    private Long id;

    @NotNull(message = "Firstname is mandatory")
    private String firstName;

    @NotNull(message = "Lastname is mandatory")
    private String lastName;

    @NotNull(message = "Phone is mandatory")
    private String phoneNumber;

    @Email(message = "Email wrong format")
    private String email;

    public PatientDTO() {
    }
}
