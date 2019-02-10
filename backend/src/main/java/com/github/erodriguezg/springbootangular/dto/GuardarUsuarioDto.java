package com.github.erodriguezg.springbootangular.dto;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Persona;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
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

}


