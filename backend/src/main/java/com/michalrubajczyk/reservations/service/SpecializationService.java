package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.SpecializationDTO;

import java.util.List;

public interface SpecializationService {

    List<SpecializationDTO> getSpecializations();
}
