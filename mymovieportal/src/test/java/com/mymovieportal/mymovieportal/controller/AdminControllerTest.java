package com.mymovieportal.mymovieportal.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mymovieportal.dto.CityDTO;
import com.mymovieportal.dto.MovieDTO;
import com.mymovieportal.dto.TheatreDTO;
import com.mymovieportal.repository.CityRepository;
import com.mymovieportal.repository.MovieRepository;
import com.mymovieportal.repository.TheatreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MymovieportalApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testInsertCity() throws JSONException {

        CityDTO cityDTO = new CityDTO();

        cityDTO.setCityName("City1234");

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertCity"),
            HttpMethod.POST, entity, String.class);

        String expected = "{cityName:City1234,cityStatus:active}";

        String str = response.getBody().substring(13);
        str = "{".concat(str);
        JSONAssert.assertEquals(expected, str, true);
    }

    @Test
    public void testInsertCityWithSpacesOnly() throws JSONException {

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName("  ");

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertCity"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testInsertCityWithNullCityName() throws JSONException {

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(null);

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertCity"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteCity() throws JSONException {

        CityDTO cityDTO = new CityDTO();

        cityDTO.setCityName("IntDelCity");

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteCity"),
            HttpMethod.POST, entity, String.class);

        String expected = "{cityName:IntDelCity,cityStatus:inactive}";

        String str = response.getBody().substring(12);

        str = "{".concat(str);

        JSONAssert.assertEquals(expected, str, true);
    }

    @Test
    public void testDeleteCityForNonExistingCity() throws JSONException {

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName("Yuganda");

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteCity"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteCityWithNullCityName() throws JSONException {

        CityDTO cityDTO = new CityDTO();

        cityDTO.setCityName(null);

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteCity"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteCityWithSpacesOnly() throws JSONException {

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName("  ");

        HttpEntity<CityDTO> entity = new HttpEntity<>(cityDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteCity"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testInsertTheatre() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreName("Theatre1234");

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertTheatre"),
            HttpMethod.POST, entity, String.class);

        String expected = "{theatreName:Theatre1234,theatreStatus:active}";

        String str = response.getBody().substring(15);
        System.out.println(str);

        str = "{".concat(str);

        JSONAssert.assertEquals(expected, str, true);
    }

    @Test
    public void testInsertTheatreWithSpacesOnly() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreName("  ");

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertTheatre"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testInsertTheatreWithNullTheatreName() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreName(null);

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertTheatre"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteTheatre() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreName("IntDelTheatre");

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteTheatre"),
            HttpMethod.POST, entity, String.class);

        String expected = "{theatreName:IntDelTheatre,theatreStatus:inactive}";

        String str = response.getBody().substring(15);
        System.out.println(str);

        str = "{".concat(str);

        JSONAssert.assertEquals(expected, str, true);
    }

    @Test
    public void testDeleteTheatreForNonExistingTheatre() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreName("Inox151");

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteTheatre"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteTheatreWithNullTheatreName() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreName(null);

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteTheatre"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteTheatreWithSpacesOnly() throws JSONException {

        TheatreDTO theatreDTO = new TheatreDTO();
        theatreDTO.setTheatreName("  ");

        HttpEntity<TheatreDTO> entity = new HttpEntity<>(theatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteTheatre"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testInsertMovie() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName("Movie1234");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertMovie"),
            HttpMethod.POST, entity, String.class);

        String expected = "{movieName:Movie1234,moviePrice:\"250\",movieStatus:active}";

        String str = response.getBody().substring(14);

        str = "{".concat(str);

        JSONAssert.assertEquals(expected, str, true);

    }

    @Test
    public void testInsertMovieWithSpacesOnly() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName("  ");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertMovie"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testInsertMovieWithNullMovieName() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName(null);

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/insertMovie"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteMovie() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName("IntDelMovie");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteMovie"),
            HttpMethod.POST, entity, String.class);

        String expected = "{movieName:IntDelMovie,moviePrice:\"250\",movieStatus:inactive}";

        String str = response.getBody().substring(13);

        str = "{".concat(str);

        JSONAssert.assertEquals(expected, str, true);

    }

    @Test
    public void testDeleteMovieForNonExistingMovie() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName("XXXXX");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteMovie"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteMovieWithNullMovieName() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName(null);

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteMovie"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteMovieWithSpacesOnly() throws JSONException {

        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieName("");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(movieDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/admin/deleteMovie"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}