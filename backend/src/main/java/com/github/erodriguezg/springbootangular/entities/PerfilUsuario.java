package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "perfil_usuario")
public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = -4539784086246915572L;

    @Id
    @Column(name = "id_perfil_usuario")
    private Integer idPerfilUsuario;

    @Column(name = "nombre")
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
    private Set<Usuario> usuarioSet;

    public PerfilUsuario() {
    }

    public PerfilUsuario(Integer idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public PerfilUsuario(Integer idPerfilUsuario, String nombre) {
        this.idPerfilUsuario = idPerfilUsuario;
        this.nombre = nombre;
    }

    public Integer getIdPerfilUsuario() {
        return idPerfilUsuario;
    }

    public void setIdPerfilUsuario(Integer idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Usuario> getUsuarioSet() {
        return usuarioSet;
    }

    public void setUsuarioSet(Set<Usuario> usuarioSet) {
        this.usuarioSet = usuarioSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PerfilUsuario that = (PerfilUsuario) o;
        return Objects.equals(idPerfilUsuario, that.idPerfilUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerfilUsuario);
    }

    @Override
    public String toString() {
        return "PerfilUsuario{" + "idPerfilUsuario=" + idPerfilUsuario + ", nombre='" + nombre + '\'' + ", usuarioSet="
                + usuarioSet + '}';
    }
}
