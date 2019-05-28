package com.github.erodriguezg.springbootangular.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "region")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class Region implements Serializable {

    private static final long serialVersionUID = -4189202485773018556L;

    @Id
    @Column(name = "id_region")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public Region() {
    }

    public Region(Integer id) {
        this.id = id;
    }

}
