package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.dto.CredencialesDto;
import com.github.erodriguezg.springbootangular.dto.RefreshTokenDto;
import com.github.erodriguezg.springbootangular.dto.RespuestaLoginDto;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.security.Identidad;
import com.github.erodriguezg.springbootangular.services.UsuarioService;
import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/security")
public class SecurityRest {

    private static final Logger log = LoggerFactory.getLogger(SecurityRest.class);

    private static final String ERROR_LOGIN = "Usuario o contraseña incorrecto";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService<Identidad> tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public RespuestaLoginDto login(@RequestBody @Valid CredencialesDto credenciales, BindingResult bindResult) {

        if (bindResult.hasErrors()) {
            RespuestaLoginDto respParamInvalidos = new RespuestaLoginDto();
            respParamInvalidos.setExitoLogin(false);
            respParamInvalidos.setErrores(Collections.singletonList(ConstantesUtil.MSJ_PARAMETROS_INVALIDOS));
            return respParamInvalidos;
        }

        Usuario usuarioEncontrado = this.usuarioService.traerPorUsername(credenciales.getUsername());
        if (usuarioEncontrado == null) {
            RespuestaLoginDto errorNoEncontroUsuario = new RespuestaLoginDto();
            errorNoEncontroUsuario.setErrores(Collections.singletonList("credenciales incorrectas"));
            errorNoEncontroUsuario.setExitoLogin(false);
            return errorNoEncontroUsuario;
        }

        Optional<String> errorCredenciales = validarCredenciales(credenciales, usuarioEncontrado);

        if (errorCredenciales.isPresent()) {
            RespuestaLoginDto respuestaErrorCredenciales = new RespuestaLoginDto();
            respuestaErrorCredenciales.setExitoLogin(false);
            respuestaErrorCredenciales.setErrores(Collections.singletonList(errorCredenciales.get()));
            return respuestaErrorCredenciales;
        }

        try {
            RespuestaLoginDto respuesta = new RespuestaLoginDto();
            String tokenJwt = tokenService.create(new Identidad(usuarioEncontrado));
            respuesta.setToken(tokenJwt);
            respuesta.setExitoLogin(true);
            return respuesta;
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error al identificar usuario: {}", credenciales.getUsername(), ex);
            RespuestaLoginDto respuestaErrorInterno = new RespuestaLoginDto();
            respuestaErrorInterno.setExitoLogin(false);
            respuestaErrorInterno.setErrores(Collections.singletonList(ConstantesUtil.MSJ_ERROR_INTERNO));
            return respuestaErrorInterno;
        }

    }

    @PostMapping("/refreshToken")
    @PreAuthorize("isAuthenticated()")
    public RefreshTokenDto refreshToken(@AuthenticationPrincipal Identidad identidad) {
        try {
            String token = this.tokenService.create(identidad);
            RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
            refreshTokenDto.setToken(token);
            return refreshTokenDto;
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error al refrescar el token: ", ex);
            throw ex;
        }
    }

    private Optional<String> validarCredenciales(CredencialesDto credenciales, Usuario usuario) {
        if (usuario == null) {
            return Optional.of(ERROR_LOGIN);
        }
        if (!passwordEncoder.matches(credenciales.getPassword(), usuario.getPassword())) {
            return Optional.of(ERROR_LOGIN);
        }
        return Optional.empty();
    }

}
