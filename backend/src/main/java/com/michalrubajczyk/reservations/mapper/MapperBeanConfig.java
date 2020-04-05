package com.michalrubajczyk.reservations.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBeanConfig {

    @Bean
    public SpecializationMapper specializationMapper() {
        return new SpecializationMapper();
    }
}
