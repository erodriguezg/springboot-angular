package com.github.erodriguezg.springbootangular.entities;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonaTest {

    private Persona persona;

    @Before
    public void before() {
        persona = new Persona();
        persona.setNombres("Juanito");
        persona.setApellidoPaterno("Perez");
        persona.setApellidoMaterno("Gonzalez");
    }

    @Test
    public void testNombreCompleto() {
        assertThat(this.persona.getNombreCompleto()).endsWith("Juanito Perez Gonzalez");
    }
}
