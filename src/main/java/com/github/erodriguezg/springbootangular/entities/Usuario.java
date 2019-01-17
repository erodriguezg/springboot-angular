package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 7439126925283605347L;

    @Id
    private Long idPersona;

    @NotBlank
    @Size(min = 4, max = 20)
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonProperty("perfil")
    @NotNull
    @JoinColumn(name = "id_perfil_usuario", referencedColumnName = "id_perfil_usuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilUsuario perfil;

    @NotNull
    @JoinColumn(name = "id_persona")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @MapsId
    private Persona persona;

    @Column(name = "habilitado")
    private Boolean habilitado;

    public Usuario() {
        // empty
    }

    public Usuario(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Usuario(Long idPersona, String username) {
        this.idPersona = idPersona;
        this.username = username;
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

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idPersona, usuario.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona);
    }

    @Override
    public String toString() {
        return "Usuario{" + "idPersona=" + idPersona + ", username='" + username + '\'' + ", perfil="
                + perfil + ", persona=" + persona + '}';
    }
}
