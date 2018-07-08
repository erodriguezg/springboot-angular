package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.rest.ShowcaseRest;
import com.github.erodriguezg.springbootangular.services.exceptions.LogicaNegocioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showcase")
public class ShowcaseRestImpl implements ShowcaseRest {

    private static final Logger log = LoggerFactory.getLogger(ShowcaseRestImpl.class);

    @GetMapping("/error-negocio")
    @Override
    public void lanzarErrorNegocio() {
        log.debug("--> lanzando error de negocio");
        throw new LogicaNegocioException("Hola, soy una excepción controlada en el flujo!");
    }

    @GetMapping("/error-interno")
    @Override
    public void lanzarErrorInterno() {
        log.debug("--> lanzando error interno");
        throw new IllegalStateException("Hola, soy un bug!");
    }

    @GetMapping("/accion-admin")
    @PreAuthorize("hasAnyAuthority('Administrador')")
    @Override
    public void accionAdministrador() {
        log.debug("--> si llegue aquí es porque tengo permiso de Administrador!");
    }

    @GetMapping("/sleep")
    @Override
    public void sleep() {
        log.debug("-> comenzando (5 seg) zzZZzzzZzzzZZzzz");
        try {
            Thread.sleep((long) 1000 * 5);
            log.debug("-> Despertando!");
        } catch (InterruptedException e) {
            log.error("ocurrio un error en el sleep: ", e);
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

}
