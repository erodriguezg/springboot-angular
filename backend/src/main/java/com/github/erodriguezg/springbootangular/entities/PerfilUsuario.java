package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "perfil_usuario")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = -4539784086246915572L;

    @Id
    @Column(name = "id_perfil_usuario")
    @EqualsAndHashCode.Include
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

}
