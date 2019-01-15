package com.github.erodriguezg.springbootangular.services;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    public List<PerfilUsuario> traerTodos() {
        return perfilUsuarioRepository
                .findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

}
