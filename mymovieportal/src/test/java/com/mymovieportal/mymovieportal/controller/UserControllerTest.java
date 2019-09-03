package com.mymovieportal.mymovieportal.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mymovieportal.MymovieportalApplication;
import com.mymovieportal.dto.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MymovieportalApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testSave() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid1() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber(null);
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid1Sapce() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("  ");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid2() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail(null);
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid2Space() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("  ");
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid3() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName(null);
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid3Space() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("  ");
        userDTO.setName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid4() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName(null);
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid4Sapce() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName("  ");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid5() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword(null);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInvalid5Space() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("0000000000");
        userDTO.setEmail("intTest@gmail.com");
        userDTO.setLastName("Test");
        userDTO.setName("Integration");
        userDTO.setPassword(" ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/save"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetUser() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getUser/1"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetUserInvalid() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getUser/-1"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetUserInvalid2() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getUser/9999"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testLoginChecking() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("piyushkushwah13592@gmail.com");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/loginChecking"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testLoginCheckingInvalid() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(null);
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/loginChecking"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testLoginCheckingInvalidSpace() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("  ");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/loginChecking"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testLoginCheckingInvalid2() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("piyushkushwah13592@gmail.com");
        userDTO.setPassword(null);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/loginChecking"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testLoginCheckingInvalid2Space() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("piyushkushwah13592@gmail.com");
        userDTO.setPassword("  ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/loginChecking"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testLoginCheckingInvalid3() throws JSONException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("wrong@gmail.com");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/loginChecking"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // @Test
    @Ignore
    public void testUpdateUser() throws JSONException {

        UserDTO userDTO = new UserDTO();
        userDTO.setContactNumber("9999999999");
        userDTO.setEmail("test@gmail.com");
        userDTO.setName("TestUser");
        userDTO.setLastName("Integration");
        userDTO.setPassword("1qqQQQ");

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/update/74"), HttpMethod.PATCH, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetContactExistence() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getContactExistence/9009897989"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetEmailExistence() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getEmailExistence/piyushkushwah13592@gmail.com"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetPassword() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getPassword/piyushkushwah13592@gmail.com"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetPasswordInvalid() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/user/getPassword/000@gmail.com"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
