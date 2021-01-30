package com.michalrubajczyk.reservations.controller;

import com.michalrubajczyk.reservations.dto.SpecializationDTO;
import com.michalrubajczyk.reservations.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecializationController {

    private SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping("/specializations")
    public ResponseEntity<List<SpecializationDTO>> getSpecializations() {
        List<SpecializationDTO> specializations = specializationService.getSpecializations();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(specializations);
    }
}
