package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.SpecializationDTO;
import com.michalrubajczyk.reservations.entity.Specialization;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SpecializationMapperTest {

    private SpecializationMapper specializationMapper;

    @BeforeEach
    void setUp() {
        specializationMapper = new SpecializationMapper();
    }

    @Test
    void specializationEntityShouldBeConvertedToSpecDTO() {
        Specialization specialization = generateSpecialization();

        SpecializationDTO specializationDTO = specializationMapper.entityToDto(specialization);

        assertSpecializationDTO(specialization, specializationDTO);
    }

    @Test
    void specializationDTOShouldBeConvertedToSpecEntity() {
        SpecializationDTO dto = generateSpecializationDTO();

        Specialization entity = specializationMapper.dtoToEntity(dto);

        assertSpecializationEntity(dto, entity);
    }

    private Specialization generateSpecialization() {
        Specialization s = new Specialization();
        s.setId(1L);
        s.setType(SpecializationType.SURGEON);
        s.setName(SpecializationType.SURGEON.getName());

        return s;
    }

    private SpecializationDTO generateSpecializationDTO() {
        SpecializationDTO dto = new SpecializationDTO();
        dto.setId(1L);
        dto.setType(SpecializationType.SURGEON.toString());
        dto.setName(SpecializationType.SURGEON.getName());

        return dto;
    }



    private void assertSpecializationDTO(Specialization specialization, SpecializationDTO specializationDTO) {
        assertThat(specializationDTO.getId(), equalTo(specialization.getId()));
        assertThat(specializationDTO.getType(), equalTo(specialization.getType().toString()));
        assertThat(specializationDTO.getName(), equalTo(specialization.getName()));
    }

    private void assertSpecializationEntity(SpecializationDTO dto, Specialization entity) {
        assertThat(entity.getId(), equalTo(dto.getId()));
        assertThat(entity.getType().toString(), equalTo(dto.getType()));
        assertThat(entity.getName(), equalTo(dto.getName()));
    }

}