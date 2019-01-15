package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.javautils.CodecUtils;
import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.dto.CredencialesDto;
import com.github.erodriguezg.springbootangular.dto.RefreshTokenDto;
import com.github.erodriguezg.springbootangular.dto.RespuestaLoginDto;
import com.github.erodriguezg.springbootangular.security.SecurityMappings;
import com.github.erodriguezg.springbootangular.services.UsuarioService;
import com.github.erodriguezg.springbootangular.dto.UsuarioDto;
import com.github.erodriguezg.springbootangular.utils.ConstantesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/security")
public class SecurityRest {

    private static final Logger log = LoggerFactory.getLogger(SecurityRest.class);

    private static final String ERROR_LOGIN = "Usuario o contraseña incorrecto";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CodecUtils codecUtils;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityMappings securityMappings;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public RespuestaLoginDto login(@RequestBody @Valid CredencialesDto credenciales, BindingResult bindResult) {

        if (bindResult.hasErrors()) {
            RespuestaLoginDto respParamInvalidos = new RespuestaLoginDto();
            respParamInvalidos.setExitoLogin(false);
            respParamInvalidos.setErrores(Arrays.asList(ConstantesUtil.MSJ_PARAMETROS_INVALIDOS));
            return respParamInvalidos;
        }

        UsuarioDto usuarioEncontrado = this.usuarioService.traerPorUsername(credenciales.getUsername());
        if (usuarioEncontrado == null) {
            RespuestaLoginDto errorNoEncontroUsuario = new RespuestaLoginDto();
            errorNoEncontroUsuario.setErrores(Arrays.asList("credenciales incorrectas"));
            errorNoEncontroUsuario.setExitoLogin(false);
            return errorNoEncontroUsuario;
        }

        Optional<String> errorCredenciales = validarCredenciales(credenciales, usuarioEncontrado);

        if (errorCredenciales.isPresent()) {
            RespuestaLoginDto respuestaErrorCredenciales = new RespuestaLoginDto();
            respuestaErrorCredenciales.setExitoLogin(false);
            respuestaErrorCredenciales.setErrores(Arrays.asList(errorCredenciales.get()));
            return respuestaErrorCredenciales;
        }

        try {
            RespuestaLoginDto respuesta = new RespuestaLoginDto();
            String tokenJwt = tokenService.create(securityMappings.userToTokenSubjectMap(usuarioEncontrado));
            respuesta.setToken(tokenJwt);
            respuesta.setExitoLogin(true);
            return respuesta;
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error al identificar usuario: {}", credenciales.getUsername(), ex);
            RespuestaLoginDto respuestaErrorInterno = new RespuestaLoginDto();
            respuestaErrorInterno.setExitoLogin(false);
            respuestaErrorInterno.setErrores(Arrays.asList(ConstantesUtil.MSJ_ERROR_INTERNO));
            return respuestaErrorInterno;
        }

    }

    @PostMapping("/refreshToken")
    @PreAuthorize("isAuthenticated()")
    public RefreshTokenDto refreshToken() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = this.tokenService.create(securityMappings.authToTokenSubjectMap(authentication));
            RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
            refreshTokenDto.setToken(token);
            return refreshTokenDto;
        } catch (RuntimeException ex) {
            log.error("Ocurrio un error al refrescar el token: ", ex);
            throw ex;
        }
    }

    private Optional<String> validarCredenciales(CredencialesDto credenciales, UsuarioDto usuarioDto) {
        if (usuarioDto == null) {
            return Optional.of(ERROR_LOGIN);
        }
        String passMD5 = codecUtils.generarHash(CodecUtils.TypeHash.MD5, credenciales.getPassword());
        if (!passMD5.equals(usuarioDto.getPassword())) {
            return Optional.of(ERROR_LOGIN);
        }
        return Optional.empty();
    }

}
