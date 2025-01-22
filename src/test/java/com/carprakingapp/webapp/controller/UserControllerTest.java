package com.carprakingapp.webapp.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/User/userSearch")
                        .with(user("GauravK").authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("User/userSearch"));
    }

    @Test
    public void testSearchResult() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/User/searchUserByLastname")
                        .param("lastname", "Pagnis")
                        .with(user("GauravK").authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attributeExists("userList"))
                .andExpect(model().attribute("lastname", "Pagnis"))
                .andExpect(view().name("User/foundUsers"));
    }



}
