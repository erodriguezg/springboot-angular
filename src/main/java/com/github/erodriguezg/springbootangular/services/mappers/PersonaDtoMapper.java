package com.github.erodriguezg.springbootangular.services.mappers;

import com.github.erodriguezg.springbootangular.services.dto.PersonaDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.entities.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaDtoMapper {

    public PersonaDto toDto(Persona entidad) {
        if(entidad == null) {
            return null;
        }
        PersonaDto dto = new PersonaDto();
        dto.setApMaterno(entidad.getApellidoMaterno());
        dto.setApPaterno(entidad.getApellidoPaterno());
        dto.setEmail(entidad.getEmail());
        dto.setFechaNacimiento(entidad.getFechanacimiento());
        dto.setRun(entidad.getRun());
        dto.setNombres(entidad.getNombres());
        return dto;
    }

    public Persona toEntidad(UsuarioDto usuarioDto, PersonaDto personaDto) {
        if(personaDto == null) {
            return null;
        }
        Persona entidad = new Persona();
        entidad.setApellidoMaterno(personaDto.getApMaterno());
        entidad.setApellidoPaterno(personaDto.getApPaterno());
        entidad.setComuna(null);
        entidad.setEmail(personaDto.getEmail());
        entidad.setFechanacimiento(personaDto.getFechaNacimiento());
        entidad.setIdPersona(usuarioDto.getId());
        entidad.setNombres(personaDto.getNombres());
        entidad.setRun(personaDto.getRun());
        entidad.setTelefono(null);
        entidad.setUsuario(null);
        return entidad;
    }

}
