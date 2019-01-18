package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.entities.Provincia;
import com.github.erodriguezg.springbootangular.entities.Region;
import com.github.erodriguezg.springbootangular.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincias")
public class ProvinciaRest {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/region/{idRegion}")
    @PreAuthorize("isAuthenticated()")
    public List<Provincia> traerPorIdRegion(@PathVariable("idRegion") Integer idRegion) {
        return provinciaService.traerPorRegion(new Region(idRegion));
    }

}
