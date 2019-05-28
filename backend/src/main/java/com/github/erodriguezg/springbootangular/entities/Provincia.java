package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "provincia")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class Provincia implements Serializable {

    private static final long serialVersionUID = -4339008590656387006L;

    @Id
    @Column(name = "id_provincia")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region")
    private Region region;

    public Provincia() {
    }

    public Provincia(Integer id) {
        this.id = id;
    }

}

