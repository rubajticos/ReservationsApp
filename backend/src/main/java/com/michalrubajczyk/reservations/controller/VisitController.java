package com.michalrubajczyk.reservations.controller;

import com.michalrubajczyk.reservations.dto.VisitCreationDTO;
import com.michalrubajczyk.reservations.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
