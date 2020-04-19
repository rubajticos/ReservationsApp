package com.michalrubajczyk.reservations.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBeanConfig {

    @Bean
    public SpecializationMapper specializationMapper() {
        return new SpecializationMapper();
    }

    @Bean
    public VisitMapper visitMapper() {
        return new VisitMapper(new PatientMapper(), new DoctorMapper(new SpecializationMapper()));
    }

    @Bean
    public PatientMapper patientMapper() { return new PatientMapper(); }
}
