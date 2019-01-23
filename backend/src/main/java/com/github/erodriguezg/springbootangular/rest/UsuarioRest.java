package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.dto.GuardarUsuarioDto;
import com.github.erodriguezg.springbootangular.dto.UsuarioFiltroDto;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.security.Identidad;
import com.github.erodriguezg.springbootangular.services.UsuarioService;
import com.github.erodriguezg.springbootangular.utils.PropertyUtils;
import com.github.erodriguezg.springbootangular.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private PropertyUtils propertyUtils;

    @Autowired
    private SecurityUtils securityUtils;

    @GetMapping("/id/{idUsuario}")
    public Usuario traerPorId(@PathVariable("idUsuario") Long idUsuario) {
        return usuarioService.traerPorId(idUsuario);
    }

    @GetMapping("/username/{username}")
    public Usuario traerPorUsername(@PathVariable("username") String username) {
        return usuarioService.traerPorUsername(username);
    }

    @PostMapping("/email")
    public Usuario taerPorEmail(@RequestParam("email") String email) {
        return usuarioService.traerPorEmail(email);
    }

    @GetMapping("/run/{run}")
    public Usuario traerPorRun(@PathVariable("run") Integer run) {
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
    public List<Usuario> buscar(
            @RequestBody UsuarioFiltroDto filtros,
            @RequestParam("inicio") int inicio,
            @RequestParam("fin") int fin) {
        log.debug("-> filtros entrada: {}", filtros);
        log.debug("-> inicio: {}", inicio);
        log.debug("-> fin: {}", fin);
        return usuarioService.buscar(filtros, inicio, fin);
    }

    @PostMapping("/buscar-no-paginado")
    public List<Usuario> buscar(@RequestBody UsuarioFiltroDto filtros) {
        log.debug("-> buscando usuarios por filtros: {}", filtros);
        return usuarioService.buscar(filtros);
    }

    @PostMapping("/guardar")
    public void guardar(@RequestBody @Valid GuardarUsuarioDto guardarUsuarioDto, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            log.warn("Error de entrada: {}", bindResult.getAllErrors());
            throw new IllegalArgumentException(bindResult.getAllErrors().toString());
        }

        //mapping para evitar vulnerabilidad de llenado entidad por post
        Usuario usuario = new Usuario();
        propertyUtils.copyProperties(usuario, guardarUsuarioDto);

        log.debug("Guardar usuario, entrada: {}", usuario);
        usuarioService.guardarUsuario(usuario);
    }

    @PostMapping("/eliminar")
    public void eliminar(@RequestParam("idUsuario") Long idUsuario) {
        Identidad identidad = securityUtils.getActualIdentidad();
        log.debug("-> principal: {}", identidad);
        Usuario usuario = new Usuario();
        usuario.setIdPersona(idUsuario);
        usuarioService.eliminar(usuario, identidad.getIdPersona());
    }

    @PostMapping("/cambiar-pass")
    public void cambiarPass(@RequestParam("idUsuario") Long idUsuario, @RequestParam("newPass") String newPass) {
        Usuario usuario = usuarioService.traerPorId(idUsuario);
        usuario.setPassword(newPass);
        usuarioService.guardarUsuario(usuario);
    }

}
