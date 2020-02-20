package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.UserCredentialsDTO;
import com.michalrubajczyk.reservations.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterUserMapper implements Mapper<User, UserCredentialsDTO> {

    @Override
    public User dtoToEntity(UserCredentialsDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

    @Override
    public List<User> dtoListToEntityList(List<UserCredentialsDTO> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public UserCredentialsDTO entityToDto(User entity) {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername(entity.getUsername());
        userCredentialsDTO.setPassword(entity.getPassword());

        return userCredentialsDTO;
    }

    @Override
    public List<UserCredentialsDTO> entityListToDtoList(List<User> entityList) {
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
