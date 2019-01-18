package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaSeq")
    @SequenceGenerator(name = "personaSeq", sequenceName = "persona_id_persona_seq", allocationSize = 1)
    @Column(name = "id_persona")
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

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Transient
    public String getNombreCompleto() {
        return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Persona persona = (Persona) o;
        return Objects.equals(idPersona, persona.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona);
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", run=" + run + ", nombres='" + nombres + '\''
                + ", apellidoPaterno='" + apellidoPaterno + '\'' + ", apellidoMaterno='" + apellidoMaterno + '\''
                + ", fechaNacimiento=" + fechaNacimiento + ", telefono='" + telefono + '\'' + ", email='" + email + '\''
                + '}';
    }

}