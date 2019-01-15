package com.github.erodriguezg.springbootangular.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "persona")
@NamedQueries({ @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
		@NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
		@NamedQuery(name = "Persona.findByRun", query = "SELECT p FROM Persona p WHERE p.run = :run"),
		@NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
		@NamedQuery(name = "Persona.findByApellidoPaterno", query = "SELECT p FROM Persona p WHERE p.apellidoPaterno = :apellidoPaterno"),
		@NamedQuery(name = "Persona.findByApellidoMaterno", query = "SELECT p FROM Persona p WHERE p.apellidoMaterno = :apellidoMaterno"),
		@NamedQuery(name = "Persona.findByFechanacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento"),
		@NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono"),
		@NamedQuery(name = "Persona.findByEmail", query = "SELECT p FROM Persona p WHERE p.email = :email") })
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaSeq")
	@SequenceGenerator(name = "personaSeq", sequenceName = "persona_id_persona_seq", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id_persona")
	private Long idPersona;

	@OneToOne(mappedBy = "persona")
	private Usuario usuario;

	@Basic(optional = false)
	@Column(name = "run")
	private Integer run;

	@Basic(optional = false)
	@Column(name = "nombres")
	private String nombres;

	@Basic(optional = false)
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Basic(optional = false)
	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "fechanacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(name = "telefono")
	private String telefono;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "id_comuna")
	private Comuna comuna;

	public Persona() {
	}

	public Persona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Persona(Long idPersona, String nombres, String apellidoPaterno, String email) {
		this.idPersona = idPersona;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.email = email;
	}

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