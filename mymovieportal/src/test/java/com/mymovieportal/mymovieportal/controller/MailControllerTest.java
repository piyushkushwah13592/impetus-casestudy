package com.mymovieportal.mymovieportal.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mymovieportal.dto.UserDTO;

public class MailControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testForgotPasswordMail() throws JSONException {

        UserDTO userDTO = new UserDTO();

        userDTO.setEmail("piyushkushwah13592@gmail.com");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

    /*    ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("8080//mymovieportal/mail/forgotPasswordMail"),
            HttpMethod.POST, entity, String.class);
        */
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:8080//mymovieportal/mail/forgotPasswordMail", HttpMethod.POST, entity, String.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testForgotPasswordMailNullData() throws JSONException {

        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(null);
        userDTO.setPassword(null);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:8080/mymovieportal/mail/forgotPasswordMail", HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + uri;
    }
}
