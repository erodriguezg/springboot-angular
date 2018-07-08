package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.rest.ComunaRest;
import com.github.erodriguezg.springbootangular.services.ComunaService;
import com.github.erodriguezg.springbootangular.services.dto.ComunaDto;
import com.github.erodriguezg.springbootangular.services.dto.ProvinciaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comunas")
public class ComunaRestImpl implements ComunaRest {

    private static final Logger log = LoggerFactory.getLogger(ComunaRestImpl.class);

    @Autowired
    private ComunaService comunaService;

    @GetMapping("/id/{idComuna}")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ComunaDto traerComunaPorIdComuna(@PathVariable("idComuna") Integer idComuna) {
        log.debug("traer comuna por id: {}", idComuna);
        return this.comunaService.traerPorId(idComuna);
    }

    @GetMapping("/provincia/{idProvincia}")
    @PreAuthorize("isAuthenticated()")
    @Override
    public List<ComunaDto> traerComunasPorProvincia(@PathVariable("idProvincia") int idProvincia) {
        log.debug("traer comunas por provincia id: {}", idProvincia);
        return this.comunaService.traerPorProvincia(new ProvinciaDto(idProvincia));
    }

}
