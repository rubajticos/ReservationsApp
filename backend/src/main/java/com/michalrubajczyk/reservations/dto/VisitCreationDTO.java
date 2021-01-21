package com.michalrubajczyk.reservations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class VisitCreationDTO {

    private Long id;

    @NotNull(message = "Registration date and time is mandatory")
    private LocalDateTime registrationDateTime;

    @NotNull(message = "Visit date and time is mandatory")
    private LocalDateTime dateTime;

    private String status;

    @NotNull(message = "PatientID is mandatory")
    private Long patientId;

    @NotNull(message = "DoctorID is mandatory")
    private Long doctorId;

}
