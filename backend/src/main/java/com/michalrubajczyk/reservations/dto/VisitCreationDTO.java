package com.michalrubajczyk.reservations.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class VisitCreationDTO {

    private Long id;

    @NotNull(message = "Registration date and time is mandatory")
    private LocalDateTime registationDateTime;

    @NotNull(message = "Visit date and time is mandatory")
    private LocalDateTime dateTime;

    private String status;

    @NotNull(message = "PatientID is mandatory")
    private Long patientId;

    @NotNull(message = "DoctorID is mandatory")
    private Long doctorId;

}
