package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "provincia")
public class Provincia implements Serializable {

	private static final long serialVersionUID = -4339008590656387006L;

	@Id
	@Column(name = "id_provincia")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_region")
	private Region region;

	public Provincia() { }

	public Provincia(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Provincia provincia = (Provincia) o;
		return Objects.equals(id, provincia.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}
