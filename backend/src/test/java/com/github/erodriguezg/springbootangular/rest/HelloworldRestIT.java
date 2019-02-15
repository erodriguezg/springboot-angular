package com.github.erodriguezg.springbootangular.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloworldRestIT extends AbstractRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deberiaRetornarMensajeDeSaludo() throws Exception {
        this.mockMvc
                .perform(
                        get("/hello")
                                .header("origin", "localhost")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello world")));
    }

}
