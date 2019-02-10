package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class Usuario implements Serializable {

    private static final long serialVersionUID = 7439126925283605347L;

    @Id
    @EqualsAndHashCode.Include
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

}
