package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.springbootangular.dto.UsuarioFiltroDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioRestIT extends AbstractRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void traerPorIdTest() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/usuarios/id/{id}", 1)
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk());
        expectedUser(resultActions);
    }

    @Test
    public void traerPorUsernameTest() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/usuarios/username/{username}", "admin")
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk());
        expectedUser(resultActions);
    }

    @Test
    public void traerPorEmailTest() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(post("/usuarios/email")
                        .param("email", "admin@correo.com")
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk());
        expectedUser(resultActions);
    }

    @Test
    public void traerPorRutTest() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/usuarios/run/{run}", 11111111)
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk());
        expectedUser(resultActions);
    }

    @Test
    public void traerRowCountTest() throws Exception {

        UsuarioFiltroDto dto = new UsuarioFiltroDto();
        dto.setRun(11111111);
        dto.setNombres("admin");
        String jsonBody = stringify(dto);

        mockMvc
                .perform(post("/usuarios/buscar/rowcount")
                        .content(jsonBody)
                        .header("content-type", "application/json")
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }


    private void expectedUser(ResultActions resultActions) throws Exception {
        resultActions
                .andExpect(jsonPath("$.idPersona", is(1)))
                .andExpect(jsonPath("$.username", is("admin")))
                .andExpect(jsonPath("$.persona.run", is(11111111)))
                .andExpect(jsonPath("$.persona.email", is("admin@correo.com")));
    }

}
