package com.github.erodriguezg.springbootangular.security;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Persona;
import com.github.erodriguezg.springbootangular.entities.Usuario;

import java.io.Serializable;

public class Identidad implements Serializable {

    private Long idPersona;

    private String username;

    private Integer run;

    private String email;

    private String nombres;

    private String apPaterno;

    private String apMaterno;

    private Integer idPerfil;

    private String nombrePerfil;

    private boolean habilitado;

    public Identidad(){ }

    public Identidad(Usuario usuario) {
        if(usuario == null) {
            return;
        }
        Persona persona = usuario.getPersona();
        PerfilUsuario perfil = usuario.getPerfil();
        this.idPersona = usuario.getIdPersona();
        this.username = usuario.getUsername();
        this.habilitado = usuario.getHabilitado();
        if(persona != null) {
            this.run = persona.getRun();
            this.email = persona.getEmail();
            this.nombres = persona.getNombres();
            this.apPaterno = persona.getApellidoPaterno();
            this.apMaterno = persona.getApellidoMaterno();
        }
        if(perfil != null) {
            this.idPerfil = perfil.getIdPerfilUsuario();
            this.nombrePerfil = perfil.getNombre();
        }
    }

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

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
