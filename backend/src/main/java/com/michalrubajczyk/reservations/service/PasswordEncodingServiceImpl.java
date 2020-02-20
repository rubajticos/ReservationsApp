package com.michalrubajczyk.reservations.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodingServiceImpl implements PasswordEncodingService {
    private static String PREFIX = "{bcrypt}";

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncodingServiceImpl() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encodeBCrypt(String plain) {
        return PREFIX + bCryptPasswordEncoder.encode(plain);
    }
}
