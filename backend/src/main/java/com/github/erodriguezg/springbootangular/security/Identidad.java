package com.github.erodriguezg.springbootangular.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Persona;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identidad implements UserDetails {

    private Long idPersona;

    private String username;

    private String password;

    private Integer run;

    private String email;

    private String nombres;

    private String apPaterno;

    private String apMaterno;

    private Integer idPerfil;

    private String nombrePerfil;

    private boolean habilitado;

    @JsonIgnore
    private List<GrantedAuthority> authorityList;

    public Identidad() {
    }

    public Identidad(Usuario usuario) {
        if (usuario == null) {
            return;
        }
        Persona persona = usuario.getPersona();
        PerfilUsuario perfil = usuario.getPerfil();
        this.idPersona = usuario.getIdPersona();
        this.username = usuario.getUsername();
        this.habilitado = usuario.getHabilitado();
        this.password = usuario.getPassword();
        if (persona != null) {
            this.run = persona.getRun();
            this.email = persona.getEmail();
            this.nombres = persona.getNombres();
            this.apPaterno = persona.getApellidoPaterno();
            this.apMaterno = persona.getApellidoMaterno();
        }
        if (perfil != null) {
            this.idPerfil = perfil.getIdPerfilUsuario();
            this.nombrePerfil = perfil.getNombre();
            this.authorityList = Collections.singletonList(new SimpleGrantedAuthority(this.nombrePerfil));
        }
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
        this.authorityList = Collections.singletonList(new SimpleGrantedAuthority(this.nombrePerfil));
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return !this.habilitado;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !this.habilitado;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return !this.habilitado;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.habilitado;
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

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return "Identidad{" +
                "idPersona=" + idPersona +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", run=" + run +
                ", email='" + email + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                ", idPerfil=" + idPerfil +
                ", nombrePerfil='" + nombrePerfil + '\'' +
                ", habilitado=" + habilitado +
                ", authorityList=" + authorityList +
                '}';
    }
}
