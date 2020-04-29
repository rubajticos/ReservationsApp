package com.michalrubajczyk.reservations.controller;

import com.michalrubajczyk.reservations.dto.VisitCreationDTO;
import com.michalrubajczyk.reservations.dto.VisitDTO;
import com.michalrubajczyk.reservations.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/visit")
    public ResponseEntity createVisit(@Valid @RequestBody VisitCreationDTO visitCreationDTO) {
        visitService.createVisit(visitCreationDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("visit/patient={patientId}")
    public ResponseEntity getVisitsForPatient(@NotNull @PathVariable Long patientId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Set<VisitDTO> visitsForPatient = visitService.getVisitsForPatient(patientId, userDetails);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visitsForPatient);
    }
}
