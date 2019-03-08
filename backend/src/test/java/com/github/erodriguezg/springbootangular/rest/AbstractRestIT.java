package com.github.erodriguezg.springbootangular.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.security.Identidad;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractRestIT {

    @Autowired
    private TokenService<Identidad> tokenService;


    public String generateAuthorizationHeader(String username, String authority) {
        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setNombre(authority);

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setHabilitado(true);
        usuario.setPerfil(perfilUsuario);
        String jwt = tokenService.create(new Identidad(usuario));
        return String.format("Bearer %s", jwt);
    }

    public String stringify(Object obj) {
        try {
            return new ObjectMapper().writer().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
