package com.github.erodriguezg.springbootangular.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class UsuarioDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 200)
    private String password;

    @NotNull
    private PersonaDto persona;

    private Boolean habilitado;

    @NotNull
    //private PerfilDto perfil;

    @SuppressWarnings("squid:S2637")
    public UsuarioDto() {
        // vacio
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PersonaDto getPersona() {
        return persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    /*
    public PerfilDto getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDto perfil) {
        this.perfil = perfil;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioDto usuario = (UsuarioDto) o;
        return Objects.equals(this.id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
