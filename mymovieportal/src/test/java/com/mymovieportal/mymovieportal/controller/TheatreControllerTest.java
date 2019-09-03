/*
 * package com.mymovieportal.mymovieportal.controller;
 *
 * import static org.junit.Assert.assertEquals;
 * import static org.junit.Assert.assertTrue;
 *
 * import org.json.JSONException;
 * import org.junit.Test;
 * import org.junit.runner.RunWith;
 * import org.skyscreamer.jsonassert.JSONAssert;
 * import org.springframework.boot.context.embedded.LocalServerPort;
 * import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.boot.test.web.client.TestRestTemplate;
 * import org.springframework.http.HttpEntity;
 * import org.springframework.http.HttpHeaders;
 * import org.springframework.http.HttpMethod;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.test.context.junit4.SpringRunner;
 *
 * import com.mymovieportal.MymovieportalApplication;
 *
 * @RunWith(SpringRunner.class)
 *
 * @SpringBootTest(classes = MymovieportalApplication.class,
 * webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * public class TheatreControllerTest {
 *
 * @LocalServerPort
 * private int port;
 *
 * TestRestTemplate restTemplate = new TestRestTemplate();
 *
 * HttpHeaders headers = new HttpHeaders();
 *
 * @Test
 * public void testGetTheatreByTheatreid() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatre/t01"),
 * HttpMethod.GET, entity, String.class);
 *
 * String expected = "{theatreId:t01,theatreName:Inox,theatreStatus:active}";
 *
 * JSONAssert.assertEquals(expected, response.getBody(), true);
 *
 * }
 *
 * @Test
 * public void testGetTheatreByInvalidTheatreId() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatre/t00"),
 * HttpMethod.GET, entity, String.class);
 *
 * String expected = "theatre operation fails.Check your given inputs";
 * assertTrue(response.getBody().contains(expected));
 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
 *
 * }
 *
 * @Test
 * public void testGetTheatreForNullTheatreId() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatre/null"),
 * HttpMethod.GET, entity, String.class);
 *
 * String expected = "invalid theatre id";
 * assertTrue(response.getBody().contains(expected));
 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
 *
 * }
 *
 * @Test
 * public void testGetTheatres() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatres"),
 * HttpMethod.GET, entity, String.class);
 *
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 *
 * }
 *
 * @Test
 * public void testGetTheatreByCityId() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatres/c01"),
 * HttpMethod.GET, entity, String.class);
 *
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 *
 * }
 *
 * @Test
 * public void testGetTheatreByInvalidCityId() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatres/c00"),
 * HttpMethod.GET, entity, String.class);
 *
 * String expected = "theatre operation fails.Check your given inputs";
 * assertTrue(response.getBody().contains(expected));
 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
 *
 * }
 *
 * @Test
 * public void testGetTheatreByNullCityId() throws JSONException {
 *
 * HttpEntity<String> entity = new HttpEntity<>(null, headers);
 *
 * ResponseEntity<String> response = restTemplate.exchange(
 * createURLWithPort("/mymovieportal/theatre/getTheatres/null"),
 * HttpMethod.GET, entity, String.class);
 *
 * String expected = "invalid city id";
 * assertTrue(response.getBody().contains(expected));
 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
 *
 * }
 *
 * private String createURLWithPort(String uri) {
 * return "http://localhost:" + port + uri;
 * }
 * }
 */

package com.mymovieportal.mymovieportal.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MymovieportalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TheatreControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetTheatreByTheatreid() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/theatre/getTheatre/1"), HttpMethod.GET, entity, String.class);

        String expected = "{theatreName:Inox,theatreStatus:active}";

        String str = response.getBody().substring(15);
        str = "{".concat(str);
        System.out.println(str);

        JSONAssert.assertEquals(expected, str, true);

    }

    @Test
    public void testGetTheatreByInvalidTheatreId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/theatre/getTheatre/999"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetTheatreByInvalidTheatreId0() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/theatre/getTheatre/0"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetTheatres() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/theatre/getTheatres"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetTheatresForDeletion() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/theatre/getTheatresForDeletion"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetTheatreByCityId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/theatre/getTheatres/1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetTheatreByCityId0() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/theatre/getTheatres/0"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetTheatreByInvalidCityId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/theatre/getTheatres/999"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
