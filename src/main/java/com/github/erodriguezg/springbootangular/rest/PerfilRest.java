package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.services.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilRest {

    @Autowired
    private PerfilUsuarioService perfilService;

    @GetMapping("/todos")
    @PreAuthorize("isAuthenticated()")
    public List<PerfilUsuario> traerTodos() {
        return this.perfilService.traerTodos();
    }

}
