package com.michalrubajczyk.reservations.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;
import com.michalrubajczyk.reservations.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterUserMapperTest {

    static RegisterUserMapper registerUserMapper;

    @BeforeAll
    static void setUp() {
        registerUserMapper = new RegisterUserMapper();
    }

    @Test
    void userCredentialsShouldBeConvertToUser() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername("a");
        userCredentialsDTO.setPassword("b");

        User user = registerUserMapper.dtoToEntity(userCredentialsDTO);

        assertEquals(userCredentialsDTO.getUsername(), user.getUsername());
        assertEquals(userCredentialsDTO.getPassword(), user.getPassword());
    }

    @Test
    void userShouldBeConvertToUserCredentialsDTO() {
        User user = new User();
        user.setUsername("a");
        user.setPassword("b");

        UserCredentialsDTO userCredentialsDTO = registerUserMapper.entityToDto(user);

        assertEquals(user.getUsername(), userCredentialsDTO.getUsername());
        assertEquals(user.getPassword(), userCredentialsDTO.getPassword());
    }

}