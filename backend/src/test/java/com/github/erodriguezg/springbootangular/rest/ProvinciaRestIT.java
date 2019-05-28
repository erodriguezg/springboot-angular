package com.github.erodriguezg.springbootangular.rest;

import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.security.Identidad;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProvinciaRestIT extends AbstractRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService<Identidad> tokenService;

    @Test
    public void traerPorRegionTest() throws Exception {
        mockMvc
                .perform(get("/provincias/region/{id}", 5)
                        .header("origin", "localhost")
                        .header("Authorization",
                                generateAuthorizationHeader("admin", "Administrador"))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(1)));
    }


}
