package com.github.erodriguezg.springbootangular.dto;

import com.github.erodriguezg.beanvalidationutils.annotations.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class PersonaDto implements Serializable {

    @EqualsAndHashCode.Include
    @NotNull
    private Integer run;

    @NotBlank
    @Size(max = 100)
    private String nombres;

    @NotBlank
    @Size(max = 100)
    private String apPaterno;

    @NotBlank
    @Size(max = 100)
    private String apMaterno;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @Past
    private Date fechaNacimiento;

}
