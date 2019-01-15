package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.repository.PerfilUsuarioRepository;
import com.github.erodriguezg.springbootangular.services.dto.PerfilDto;
import com.github.erodriguezg.springbootangular.services.mappers.PerfilDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    private PerfilDtoMapper perfilUsuarioMapper;

    @Transactional(readOnly = true)
    public List<PerfilDto> traerTodos() {
        return perfilUsuarioRepository
                .findAll(Sort.by(Sort.Direction.ASC, "nombre"))
                .stream()
                .map(perfilUsuarioMapper::toPerfilDto)
                .collect(Collectors.toList());
    }

}
