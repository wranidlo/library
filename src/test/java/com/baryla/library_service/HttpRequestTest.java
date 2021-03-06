package com.baryla.library_service;

import com.baryla.library_service.users.AccountType;
import com.baryla.library_service.users.User;
import com.baryla.library_service.users.UserModelAssembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.EntityModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void firstUserShouldContainJohn() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "users/1", String.class))
                .contains("John");
    }
}