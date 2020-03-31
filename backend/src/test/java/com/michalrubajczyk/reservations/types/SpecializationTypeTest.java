package com.michalrubajczyk.reservations.types;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class SpecializationTypeTest {

    @Test
    public void fromNameShouldThrowIllegalArgumentExceptionWhenTypeForNameNotDeclared() {
        String wrongSpecializationType = "a";

        assertThrows(IllegalArgumentException.class, () -> SpecializationType.fromName(wrongSpecializationType));
    }

    @Test
    public void fromNameShouldReturnProperSpecializationTypeForName() {
        String pediatrician = "Pediatrician";
        String surgeon = "Surgeon";

        assertThat(SpecializationType.fromName(pediatrician), is(SpecializationType.PEDIATRICIAN));
        assertThat(SpecializationType.fromName(surgeon), is(SpecializationType.SUREGON));
    }

    @Test
    public void enumShouldReturnsNameOfValue() {
        assertThat(SpecializationType.PEDIATRICIAN.getName(), equalTo("Pediatrician"));
        assertThat(SpecializationType.SUREGON.getName(), equalTo("Surgeon"));
    }

}