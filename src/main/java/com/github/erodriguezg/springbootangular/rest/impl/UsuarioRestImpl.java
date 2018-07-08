package com.github.erodriguezg.springbootangular.rest.impl;

import com.github.erodriguezg.springbootangular.rest.UsuarioRest;
import com.github.erodriguezg.springbootangular.services.UsuarioService;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.services.dto.UsuarioFiltroDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("isAuthenticated() and hasAnyAuthority('Administrador')")
public class UsuarioRestImpl implements UsuarioRest {

    private static final Logger log = LoggerFactory.getLogger(UsuarioRestImpl.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/id/{idUsuario}")
    @Override
    public UsuarioDto traerPorId(@PathVariable("idUsuario") Long idUsuario) {
        return usuarioService.traerPorId(idUsuario);
    }

    @GetMapping("/username/{username}")
    @Override
    public UsuarioDto traerPorUsername(@PathVariable("username") String username) {
        return usuarioService.traerPorUsername(username);
    }

    @PostMapping("/email")
    @Override
    public UsuarioDto taerPorEmail(@RequestParam("email") String email) {
        return usuarioService.traerPorEmail(email);
    }

    @GetMapping("/run/{run}")
    @Override
    public UsuarioDto traerPorRun(@PathVariable("run") Integer run) {
        return usuarioService.traerPorRun(run);
    }

    @PostMapping("/buscar/rowcount")
    @Override
    public long buscarRowCount(@RequestBody UsuarioFiltroDto filtros) {
        log.debug("-> filtros entrada: {}", filtros);
        long rowCount = usuarioService.buscarRowCount(filtros);
        log.debug("-> rowCount: {}", rowCount);
        return rowCount;
    }

    @PostMapping("/buscar")
    @Override
    public List<UsuarioDto> buscar(
            @RequestBody UsuarioFiltroDto filtros,
            @RequestParam("inicio") int inicio,
            @RequestParam("fin") int fin) {
        log.debug("-> filtros entrada: {}", filtros);
        log.debug("-> inicio: {}", inicio);
        log.debug("-> fin: {}", fin);
        return usuarioService.buscar(filtros, inicio, fin);
    }

    @PostMapping("/buscar-no-paginado")
    @Override
    public List<UsuarioDto> buscar(@RequestBody UsuarioFiltroDto filtros) {
        log.debug("-> buscando usuarios por filtros: {}", filtros);
        return usuarioService.buscar(filtros);
    }

    @PostMapping("/guardar")
    @Override
    public void guardar(@RequestBody @Valid UsuarioDto usuarioDto, BindingResult bindResult, @AuthenticationPrincipal UsuarioDto principal) {
        log.debug("-> principal: {}", principal);
        if (bindResult.hasErrors()) {
            log.warn("Error de entrada: {}", bindResult.getAllErrors());
            throw new IllegalArgumentException(bindResult.getAllErrors().toString());
        }
        log.debug("Guardar usuario, entrada: {}", usuarioDto);
        usuarioService.guardarUsuario(usuarioDto);
    }

    @PostMapping("/eliminar")
    @Override
    public void eliminar(@RequestParam ("idUsuario") Long idUsuario, @AuthenticationPrincipal UsuarioDto principal) {
        log.debug("-> principal: {}", principal);
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(idUsuario);
        usuarioService.eliminar(usuarioDto, principal.getId());
    }

    @PostMapping("/cambiar-pass")
    @Override
    public void cambiarPass(@RequestParam("idUsuario") Long idUsuario, @RequestParam("newPass") String newPass) {
        UsuarioDto usuarioDto = usuarioService.traerPorId(idUsuario);
        usuarioDto.setPassword(newPass);
        usuarioService.guardarUsuario(usuarioDto);
    }

}
