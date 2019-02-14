package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.entities.PerfilUsuario;
import com.github.erodriguezg.springbootangular.entities.Usuario;
import com.github.erodriguezg.springbootangular.security.Identidad;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegionRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService<Identidad> tokenService;


    @Test
    public void traerTodasTestAccesoDenegado() throws Exception {
        mockMvc
                .perform(get("/regiones/todas").header("origin", "localhost"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void traerTodasTest() throws Exception {

        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setNombre("Administrador");

        Usuario usuario = new Usuario();
        usuario.setHabilitado(true);
        usuario.setPerfil(perfilUsuario);
        String jwt = tokenService.create(new Identidad(usuario));

        mockMvc
                .perform(get("/regiones/todas")
                        .header("origin", "localhost")
                        .header("Authorization", String.format("Bearer %s", jwt))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}
