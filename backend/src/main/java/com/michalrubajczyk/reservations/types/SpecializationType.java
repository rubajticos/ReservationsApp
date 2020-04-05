package com.michalrubajczyk.reservations.types;

public enum SpecializationType {
    PEDIATRICIAN("Pediatrician"),
    SURGEON("Surgeon");

    private String value;

    SpecializationType(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }

    public static SpecializationType fromName(String name) {
        for (SpecializationType type : SpecializationType.values()) {
            if (type.value.equalsIgnoreCase(name))
                return type;
        }

        throw new IllegalArgumentException("No constant with text " + name + " found");
    }
}
