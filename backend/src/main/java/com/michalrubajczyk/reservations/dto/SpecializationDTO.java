package com.michalrubajczyk.reservations.dto;

import lombok.Data;

@Data
public class SpecializationDTO {

    private Long id;
    private String type;
    private String name;

    public SpecializationDTO(Long id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public SpecializationDTO() {
    }
}
