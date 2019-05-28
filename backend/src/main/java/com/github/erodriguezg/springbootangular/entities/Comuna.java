package com.github.erodriguezg.springbootangular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "comuna")
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Comuna implements Serializable {

    private static final long serialVersionUID = 6876833168788473163L;

    @Id
    @Column(name = "id_comuna")
    @EqualsAndHashCode.Include
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

}
