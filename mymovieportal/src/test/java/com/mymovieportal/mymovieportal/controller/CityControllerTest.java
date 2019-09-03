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
import com.mymovieportal.dto.CityTheatreDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MymovieportalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetCity() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/city/getCity/1"),
            HttpMethod.GET, entity, String.class);

        String expected = "{cityName:Indore,cityStatus:active}";
        String str = response.getBody().substring(12);
        str = "{".concat(str);
        JSONAssert.assertEquals(expected, str, true);

    }

    @Test
    public void testGetCityInvalidId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/city/getCity/999"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetCityInvalidId0() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/city/getCity/0"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetCities() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/city/getCities"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetCitiesForDeletion() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response =
            restTemplate.exchange(createURLWithPort("/mymovieportal/city/getCitiesForDeletion"),
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetCityNameOnly() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/city/getCityNameOnly/1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testGetCityNameOnlyInvalidId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/city/getCityNameOnly/999"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetCityNameOnlyInvalidId0() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/city/getCityNameOnly/0"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testMapCityTheatre() throws JSONException {

        CityTheatreDTO cityTheatreDTO = new CityTheatreDTO();

        cityTheatreDTO.setCtTheatreId(3l);
        cityTheatreDTO.setCtCityId(2l);

        HttpEntity<CityTheatreDTO> entity = new HttpEntity<>(cityTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/city/mapCityTheatre"),
            HttpMethod.POST, entity, String.class);

        String expected = "{ctCityId:2,ctTheatreId:3}";

        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    public void testMapCityTheatreInvalid() throws JSONException {

        CityTheatreDTO cityTheatreDTO = new CityTheatreDTO();
        cityTheatreDTO.setCtCityId(1l);
        cityTheatreDTO.setCtTheatreId(1l);

        HttpEntity<CityTheatreDTO> entity = new HttpEntity<>(cityTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/city/mapCityTheatre"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
