package com.michalrubajczyk.reservations.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCredentialsDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
