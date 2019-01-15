package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.services.UsuarioService;
import com.github.erodriguezg.springbootangular.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.dto.UsuarioFiltroDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("isAuthenticated() and hasAnyAuthority('Administrador')")
public class UsuarioRest {

    private static final Logger log = LoggerFactory.getLogger(UsuarioRest.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/id/{idUsuario}")
    public UsuarioDto traerPorId(@PathVariable("idUsuario") Long idUsuario) {
        return usuarioService.traerPorId(idUsuario);
    }

    @GetMapping("/username/{username}")
    public UsuarioDto traerPorUsername(@PathVariable("username") String username) {
        return usuarioService.traerPorUsername(username);
    }

    @PostMapping("/email")
    public UsuarioDto taerPorEmail(@RequestParam("email") String email) {
        return usuarioService.traerPorEmail(email);
    }

    @GetMapping("/run/{run}")
    public UsuarioDto traerPorRun(@PathVariable("run") Integer run) {
        return usuarioService.traerPorRun(run);
    }

    @PostMapping("/buscar/rowcount")
    public long buscarRowCount(@RequestBody UsuarioFiltroDto filtros) {
        log.debug("-> filtros entrada: {}", filtros);
        long rowCount = usuarioService.buscarRowCount(filtros);
        log.debug("-> rowCount: {}", rowCount);
        return rowCount;
    }

    @PostMapping("/buscar")
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
    public List<UsuarioDto> buscar(@RequestBody UsuarioFiltroDto filtros) {
        log.debug("-> buscando usuarios por filtros: {}", filtros);
        return usuarioService.buscar(filtros);
    }

    @PostMapping("/guardar")
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
    public void eliminar(@RequestParam ("idUsuario") Long idUsuario, @AuthenticationPrincipal UsuarioDto principal) {
        log.debug("-> principal: {}", principal);
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(idUsuario);
        usuarioService.eliminar(usuarioDto, principal.getId());
    }

    @PostMapping("/cambiar-pass")
    public void cambiarPass(@RequestParam("idUsuario") Long idUsuario, @RequestParam("newPass") String newPass) {
        UsuarioDto usuarioDto = usuarioService.traerPorId(idUsuario);
        usuarioDto.setPassword(newPass);
        usuarioService.guardarUsuario(usuarioDto);
    }

}
