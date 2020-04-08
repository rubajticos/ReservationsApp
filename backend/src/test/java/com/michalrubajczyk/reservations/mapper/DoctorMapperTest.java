package com.michalrubajczyk.reservations.mapper;

import com.michalrubajczyk.reservations.dto.DoctorDTO;
import com.michalrubajczyk.reservations.dto.SpecializationDTO;
import com.michalrubajczyk.reservations.entity.Doctor;
import com.michalrubajczyk.reservations.entity.Specialization;
import com.michalrubajczyk.reservations.types.SpecializationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoctorMapperTest {

    private static DoctorMapper doctorMapper;

    @BeforeAll
    static void setUp() {
        doctorMapper = new DoctorMapper(new SpecializationMapper());
    }

    @Test
    void doctorShouldBeConvertedToDoctorDTO() {
        Doctor doctor = generateDoctor();

        DoctorDTO doctorDTO = doctorMapper.entityToDto(doctor);

        assertDoctorDTO(doctorDTO, doctor);
    }

    @Test
    void doctorsListShouldBeConvertedToDoctorsDTOList() {
        List<Doctor> doctors = generateDoctorList();

        List<DoctorDTO> doctorDTOList = doctorMapper.entityListToDtoList(doctors);

        for (int i = 0; i < doctorDTOList.size(); i++) {
            assertDoctorDTO(doctorDTOList.get(i), doctors.get(i));
        }
    }

    private void assertDoctorDTO(DoctorDTO doctorDTO, Doctor doctor) {
        assertEquals(doctorDTO.getId(), doctor.getId());
        assertEquals(doctorDTO.getFirstName(), doctor.getFirstName());
        assertEquals(doctorDTO.getLastName(), doctor.getLastName());
        assertEquals(doctorDTO.getSpecialization().getId(), doctor.getSpecialization().getId());
        assertEquals(doctorDTO.getSpecialization().getName(), doctor.getSpecialization().getName());
        assertEquals(doctorDTO.getSpecialization().getType(), doctor.getSpecialization().getType().toString());
    }

    @Test
    void doctorDTOShouldBeConvertedToDoctor() {
        DoctorDTO doctorDTO = generateDoctorDTO();

        Doctor doctor = doctorMapper.dtoToEntity(doctorDTO);

        assertDoctor(doctor, doctorDTO);
    }

    @Test
    void doctorsDTOListShouldBeConvertedToDoctorsList() {
        List<DoctorDTO> doctorDTO = generateDoctorDTOList();

        List<Doctor> doctors = doctorMapper.dtoListToEntityList(doctorDTO);

        for (int i = 0; i < doctors.size(); i++) {
            assertDoctor(doctors.get(i), doctorDTO.get(i));
        }
    }

    @Test
    void conversionToEntityShouldThrowExceptionWhenSpecializationIsInvalid() {
        DoctorDTO dto = new DoctorDTO();
        SpecializationDTO specializationDTO = new SpecializationDTO();
        specializationDTO.setType("WRONG_TYPE");
        dto.setSpecialization(specializationDTO);

        assertThrows(IllegalArgumentException.class, () -> doctorMapper.dtoToEntity(dto));
    }

    private void assertDoctor(Doctor doctor, DoctorDTO doctorDTO) {
        assertEquals(doctor.getId(), doctorDTO.getId());
        assertEquals(doctor.getFirstName(), doctorDTO.getFirstName());
        assertEquals(doctor.getLastName(), doctorDTO.getLastName());
        assertEquals(doctor.getSpecialization().getId(), doctorDTO.getSpecialization().getId());
        assertEquals(doctor.getSpecialization().getName(), doctorDTO.getSpecialization().getName());
        assertEquals(doctor.getSpecialization().getType().toString(), doctorDTO.getSpecialization().getType());
    }

    private Doctor generateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("a");
        doctor.setLastName("b");
        doctor.addSpecialization(new Specialization(SpecializationType.SURGEON, SpecializationType.SURGEON.getName()));

        return doctor;
    }

    private DoctorDTO generateDoctorDTO() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        doctorDTO.setFirstName("a");
        doctorDTO.setLastName("b");
        doctorDTO.setSpecialization(new SpecializationDTO(1L, SpecializationType.PEDIATRICIAN.toString(), SpecializationType.PEDIATRICIAN.getName()));

        return doctorDTO;
    }

    private List<Doctor> generateDoctorList() {
        List<Doctor> doctors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Doctor d = new Doctor();
            d.setId((long) i);
            d.setFirstName("a");
            d.setLastName("b");
            d.addSpecialization(new Specialization(SpecializationType.SURGEON, SpecializationType.SURGEON.getName()));

            doctors.add(d);
        }

        return doctors;
    }

    private List<DoctorDTO> generateDoctorDTOList() {
        List<DoctorDTO> doctorsDTO = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DoctorDTO d = new DoctorDTO();
            d.setId((long) i);
            d.setFirstName("a");
            d.setLastName("b");
            d.setSpecialization(new SpecializationDTO(1L, SpecializationType.PEDIATRICIAN.toString(), SpecializationType.PEDIATRICIAN.getName()));
            doctorsDTO.add(d);
        }

        return doctorsDTO;
    }

}