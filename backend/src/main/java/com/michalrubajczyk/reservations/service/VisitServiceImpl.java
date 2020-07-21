package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.dto.VisitCreationDTO;
import com.michalrubajczyk.reservations.dto.VisitDTO;
import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.entity.Patient;
import com.michalrubajczyk.reservations.entity.Visit;
import com.michalrubajczyk.reservations.mapper.VisitMapper;
import com.michalrubajczyk.reservations.repository.DoctorRepository;
import com.michalrubajczyk.reservations.repository.PatientRepository;
import com.michalrubajczyk.reservations.repository.VisitRepository;
import com.michalrubajczyk.reservations.types.VisitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class VisitServiceImpl implements VisitService {

    private VisitRepository visitRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private VisitMapper visitMapper;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.visitMapper = visitMapper;
    }

    @Override
    @Transactional
    public VisitDTO createVisit(VisitCreationDTO visitCreationDTO) {
        Visit visit = translateToVisitEntity(visitCreationDTO);
        visit = visitRepository.save(visit);

        if (visit != null)
            return visitMapper.entityToDto(visit);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An error occurred. Creating Visit failed.");
    }

    private Visit translateToVisitEntity(VisitCreationDTO visitCreationDTO) {
        Visit visit = new Visit();
        visit.setId(visitCreationDTO.getId());
        visit.setStatus(VisitStatus.valueOf(visitCreationDTO.getStatus()));
        visit.setDateTime(visitCreationDTO.getDateTime());
        visit.setRegistrationDateTime(visitCreationDTO.getRegistrationDateTime());
        visit.addDoctor(findDoctor(visitCreationDTO.getDoctorId()));
        visit.addPatient(findPatient(visitCreationDTO.getPatientId()));

        return visit;
    }

    private Doctor findDoctor(Long doctorId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent())
            return optionalDoctor.get();

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor not found");
    }

    private Patient findPatient(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent())
            return optionalPatient.get();

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not found");
    }

    @Override
    public Set<VisitDTO> getVisitsForPatient(Long patientId, UserDetails userDetails) {
        Patient patient = null;
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            patient = optionalPatient.get();
        }
        checkPermissions(patient, userDetails);

        Set<Visit> visits = visitRepository.findByPatient(patient);
        List<VisitDTO> visitDTOS = visitMapper.entityListToDtoList(new ArrayList<>(visits));

        return new HashSet<>(visitDTOS);
    }

    private void checkPermissions(Patient patient, UserDetails userDetails) {
        String patientOwner = patient.getUser().getUsername();
        String user = userDetails.getUsername();
        if (!patientOwner.equals(user))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not owner of requested Patient");
    }
}
