package com.github.erodriguezg.springbootangular.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsuarioFiltroDto implements Serializable {

    private static final long serialVersionUID = -7369622710383099279L;

    private String username;
    private Integer run;
    private String nombres;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private Date fechaNacimientoInferior;
    private Date fechaNacimientoSuperior;
    private Boolean habilitado;

    @JsonProperty(value = "perfil")
    private PerfilUsuario perfil;

}
