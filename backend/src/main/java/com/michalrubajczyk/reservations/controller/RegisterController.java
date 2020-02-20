package com.michalrubajczyk.reservations.controller;

import com.michalrubajczyk.reservations.dto.RegistrationDTO;
import com.michalrubajczyk.reservations.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterService registerService;


    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationDTO registrationData) {
        boolean result = registerService.register(registrationData);

        if (result)
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
