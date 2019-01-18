package com.github.erodriguezg.springbootangular.dto;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Persona;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class GuardarUsuarioDto implements Serializable {

    private Long idPersona;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    private String password;

    @NotNull
    private PerfilUsuario perfil;

    @NotNull
    private Persona persona;

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}


