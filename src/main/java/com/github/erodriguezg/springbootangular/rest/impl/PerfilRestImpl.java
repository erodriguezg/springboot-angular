package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.rest.PerfilRest;
import com.github.erodriguezg.springbootangular.services.PerfilService;
import com.github.erodriguezg.springbootangular.services.dto.PerfilDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilRestImpl implements PerfilRest {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/todos")
    @PreAuthorize("isAuthenticated()")
    @Override
    public List<PerfilDto> traerTodos() {
        return this.perfilService.traerTodos();
    }

}
