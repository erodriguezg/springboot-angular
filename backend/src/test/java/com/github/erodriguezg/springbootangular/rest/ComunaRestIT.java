package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.security.Identidad;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComunaRestIT extends AbstractRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService<Identidad> tokenService;

    @Test
    public void traerPorProvinciaTest() throws Exception {
        mockMvc
                .perform(get("/comunas/provincia/{id}", 58)
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }

    @Test
    public void traerPorIdTest() throws Exception {
        mockMvc
                .perform(get("/comunas/id/{id}", 5801)
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", equalTo("Quilpué")));
    }
}
