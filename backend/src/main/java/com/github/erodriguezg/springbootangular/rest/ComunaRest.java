package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.entities.Comuna;
import com.github.erodriguezg.springbootangular.entities.Provincia;
import com.github.erodriguezg.springbootangular.services.ComunaService;
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
public class ComunaRest {

    private static final Logger log = LoggerFactory.getLogger(ComunaRest.class);

    @Autowired
    private ComunaService comunaService;

    @GetMapping("/id/{idComuna}")
    @PreAuthorize("isAuthenticated()")
    public Comuna traerComunaPorIdComuna(@PathVariable("idComuna") Integer idComuna) {
        log.debug("traer comuna por id: {}", idComuna);
        return comunaService.traerPorId(idComuna);
    }

    @GetMapping("/provincia/{idProvincia}")
    @PreAuthorize("isAuthenticated()")
    public List<Comuna> traerComunasPorProvincia(@PathVariable("idProvincia") int idProvincia) {
        log.debug("traer comunas por provincia id: {}", idProvincia);
        return this.comunaService.traerPorProvincia(new Provincia(idProvincia));
    }

}
