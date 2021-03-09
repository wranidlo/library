package com.baryla.library_service;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestWithMockMvc {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFirstUser() throws Exception {
        this.mockMvc.perform(get("/library/users/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    public void testGetFirstUserNotEqual() throws Exception {
        this.mockMvc.perform(get("/library/users/5")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        this.mockMvc.perform(get("/library/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.users").isNotEmpty());
    }

}
