package com.nhnent.edu.springboot.security.webapp.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("db")
@AutoConfigureMockMvc
public class DbWebSecurityConfigTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello_notAuthentication() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("http://localhost/login"))
        ;
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("admin"))
               .andExpect(authenticated().withUsername("admin"))
        ;
    }

    @Test
    @WithMockUser("anyone")
    public void hello_byUser() throws Exception {
        mockMvc
            .perform(get("/")
                         .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("hello"))
        ;
    }
}
