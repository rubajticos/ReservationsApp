package com.michalrubajczyk.reservations.controller;

import com.michalrubajczyk.reservations.dto.DoctorDTO;
import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.mapper.DoctorMapper;
import com.michalrubajczyk.reservations.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {

    private DoctorService doctorService;
    private DoctorMapper doctorMapper;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
        this.doctorMapper = new DoctorMapper();
    }

    @GetMapping("/doctor")
    public ResponseEntity getAll() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDTO> doctorDTOS = doctorMapper.entityListToDtoList(doctors);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorDTOS);
    }

    @GetMapping("/doctor/id/{id}")
    public ResponseEntity getDoctorById(@NotNull @PathVariable Long id) {
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);
        if (doctorOptional.isPresent()) {
            Doctor doc = doctorOptional.get();
            DoctorDTO doctorDTO = doctorMapper.entityToDto(doc);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(doctorDTO);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(null);
    }

    @GetMapping("/doctor/name/{firstName}/{lastName}")
    public ResponseEntity getDoctorByName(@NotNull @PathVariable String firstName, @NotNull @PathVariable String lastName) {
        List<Doctor> doctors = doctorService.getDoctorsByName(firstName, lastName);
        List<DoctorDTO> doctorDTOS = doctorMapper.entityListToDtoList(doctors);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorDTOS);
    }

    @GetMapping("/doctor/spec/{specialization}")
    public ResponseEntity getDoctorsBySpecialization(@NotNull @PathVariable String specialization) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
        List<DoctorDTO> doctorDTOS = doctorMapper.entityListToDtoList(doctors);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorDTOS);
    }
}
