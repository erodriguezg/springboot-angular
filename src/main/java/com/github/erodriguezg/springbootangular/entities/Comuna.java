package com.github.erodriguezg.springbootangular.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comuna")
public class Comuna implements Serializable {

    private static final long serialVersionUID = 6876833168788473163L;

    @Id
    @Column(name = "id_comuna")
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comuna comuna = (Comuna) o;
        return Objects.equals(id, comuna.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}