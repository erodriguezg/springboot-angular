package com.github.erodriguezg.springbootangular.services.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "perfil_usuario")
@NamedQueries({
		@NamedQuery(name = "PerfilUsuario.findAll", query = "SELECT p FROM PerfilUsuario p order by nombre asc "),
		@NamedQuery(name = "PerfilUsuario.findByIdPerfilUsuario", query = "SELECT p FROM PerfilUsuario p WHERE p.idPerfilUsuario = :idPerfilUsuario"),
		@NamedQuery(name = "PerfilUsuario.findByNombre", query = "SELECT p FROM PerfilUsuario p WHERE p.nombre = :nombre") })
public class PerfilUsuario implements Serializable {

	private static final long serialVersionUID = -4539784086246915572L;

	@Id
	@Column(name = "id_perfil_usuario")
	private Integer idPerfilUsuario;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "idPerfilUsuario")
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
