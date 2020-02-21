package com.michalrubajczyk.reservations.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

class PasswordEncodingServiceImplTest {

    private PasswordEncodingServiceImpl passwordEncodingService;

    @BeforeEach
    void setUp() {
        this.passwordEncodingService = new PasswordEncodingServiceImpl();
    }

    @Test
    void hashedBCryptPasswordShouldContainsPrefix() {
        String plain = "a";

        String password = passwordEncodingService.encodeBCrypt(plain);

        assertThat(password, startsWith("{bcrypt}"));
    }

}