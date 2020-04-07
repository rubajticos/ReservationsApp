package com.michalrubajczyk.reservations.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VisitDTO {

    private Long id;

    @NotNull(message = "Registration date and time is mandatory")
    private LocalDateTime registationDateTime;
    @NotNull(message = "Visit date and time is mandatory")
    private LocalDateTime dateTime;

    private String status;
    @NotNull(message = "Patient is mandatory")
    private PatientDTO patient;
    @NotNull(message = "Doctor is mandatory")
    private DoctorDTO doctor;
}
