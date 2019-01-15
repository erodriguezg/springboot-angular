package com.github.erodriguezg.springbootangular.services.mappers;

import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoMapper {

    @Autowired
    private PerfilDtoMapper perfilDtoMapper;

    @Autowired
    private PersonaDtoMapper personaDtoMapper;

    public UsuarioDto toUsuarioDto(Usuario entidad) {
        if(entidad == null) {
            return null;
        }
        UsuarioDto dto = new UsuarioDto();
        dto.setPersona(personaDtoMapper.toDto(entidad.getPersona()));
        dto.setHabilitado(entidad.getHabilitado());
        dto.setId(entidad.getIdPersona());
        dto.setPassword(entidad.getPassword());
        dto.setPerfil(perfilDtoMapper.toPerfilDto(entidad.getPerfil()));
        dto.setUsername(entidad.getUsername());
        return dto;
    }

    public Usuario toUsuario(UsuarioDto dto) {
        if(dto == null) {
            return null;
        }
        Usuario entidad = new Usuario();
        entidad.setUsername(dto.getUsername());
        entidad.setHabilitado(dto.getHabilitado());
        entidad.setIdPersona(dto.getId());
        entidad.setPassword(dto.getPassword());
        entidad.setPersona(personaDtoMapper.toEntidad(dto, dto.getPersona()));
        entidad.setPerfil(perfilDtoMapper.toPerfilUsuario(dto.getPerfil()));
        return entidad;
    }
}
