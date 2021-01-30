package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.SpecializationDTO;
import com.michalrubajczyk.reservations.entity.Specialization;
import com.michalrubajczyk.reservations.mapper.SpecializationMapper;
import com.michalrubajczyk.reservations.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<SpecializationDTO> getSpecializations() {
        SpecializationMapper specializationMapper = new SpecializationMapper();
        List<Specialization> specializations = specializationRepository.findAll();

        return specializationMapper.entityListToDtoList(specializations);
    }


}
