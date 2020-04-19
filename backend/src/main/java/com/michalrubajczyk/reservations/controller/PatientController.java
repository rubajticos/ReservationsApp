package com.michalrubajczyk.reservations.controller;

import com.michalrubajczyk.reservations.dto.PatientDTO;
import com.michalrubajczyk.reservations.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public ResponseEntity getPatientForRequestOwner(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        PatientDTO patient = patientService.getPatientByUsername(userDetails.getUsername());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patient);
    }

}
