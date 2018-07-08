package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.rest.ProvinciaRest;
import com.github.erodriguezg.springbootangular.services.ProvinciaService;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import com.github.erodriguezg.springbootangular.services.dto.RegionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincias")
public class ProvinciaRestImpl implements ProvinciaRest{

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/region/{idRegion}")
    @PreAuthorize("isAuthenticated()")
    @Override
    public List<ProvinciaDto> traerPorIdRegion(@PathVariable("idRegion") Integer idRegion) {
        return provinciaService.traerPorRegion(new RegionDto(idRegion));
    }

}
