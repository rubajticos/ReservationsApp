package com.michalrubajczyk.reservations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Hello from Public endpoint";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Hello from PRIVATE endpoint - you're authenticated!";
    }
}
