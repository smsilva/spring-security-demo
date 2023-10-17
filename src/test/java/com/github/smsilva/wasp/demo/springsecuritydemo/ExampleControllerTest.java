package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExampleController.class)
@Import(AppConfig.class)
public class ExampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ExampleController exampleController;

    @Test
    void should_Return200_when_RequestGetForThePublicEndpoint() throws Exception {
        mockMvc.perform(get("/api/public")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_Return401_when_RequestGetForPrivateEndpoint() throws Exception {
        mockMvc.perform(get("/api/private")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_Return200_when_RequestGetForPrivateEndpointWithToken() throws Exception {
        mockMvc.perform(get("/api/jwt-test")
                        .with(jwt().authorities(new SimpleGrantedAuthority("listing_admin")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
