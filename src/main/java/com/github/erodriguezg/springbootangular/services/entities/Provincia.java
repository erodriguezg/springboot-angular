package com.github.erodriguezg.springbootangular.services.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "provincia")
@NamedQueries({
	@NamedQuery(name = "Provincia.traerPorRegion", query = "select p from Provincia p where p.region = :region order by p.nombre asc ")
})
public class Provincia implements Serializable {

	private static final long serialVersionUID = -4339008590656387006L;

	@Id
	@Column(name = "id_provincia")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "id_region")
	private Region region;

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
