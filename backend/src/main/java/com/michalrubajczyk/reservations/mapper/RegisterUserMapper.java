package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;
import com.michalrubajczyk.reservations.entity.User;

public class RegisterUserMapper extends BaseMapper<User, UserCredentialsDTO> {

    @Override
    public User dtoToEntity(UserCredentialsDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

    @Override
    public UserCredentialsDTO entityToDto(User entity) {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername(entity.getUsername());
        userCredentialsDTO.setPassword(entity.getPassword());

        return userCredentialsDTO;
    }
}
