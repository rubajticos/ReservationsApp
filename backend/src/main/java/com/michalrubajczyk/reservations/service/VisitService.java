package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.VisitCreationDTO;
import com.michalrubajczyk.reservations.dto.VisitDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface VisitService {

    VisitDTO createVisit(VisitCreationDTO visitCreationDTO);

    Set<VisitDTO> getVisitsForPatient(Long patientId, UserDetails userDetails);

}
