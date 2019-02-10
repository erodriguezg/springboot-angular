package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persona")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaSeq")
    @SequenceGenerator(name = "personaSeq", sequenceName = "persona_id_persona_seq", allocationSize = 1)
    @Column(name = "id_persona")
    @EqualsAndHashCode.Include
    private Long idPersona;

    @JsonIgnore
    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Usuario usuario;

    @NotNull
    @Column(name = "run")
    private Integer run;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombres")
    private String nombres;

    @NotBlank
    @Size(max = 100)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @NotBlank
    @Size(max = 100)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @NotNull
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;


    @Column(name = "telefono")
    private String telefono;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    @Transient
    public String getNombreCompleto() {
        return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
    }

}
