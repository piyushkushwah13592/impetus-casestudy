package com.mymovieportal.mymovieportal.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.mymovieportal.dto.MovieTheatreDTO;
import com.mymovieportal.dto.ResultDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MymovieportalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetMovies() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovies"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetMoviesForDeletion() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response =
            restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMoviesForDeletion"),
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetShowtimes() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getShowtimes"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetMovie() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovie/1"),
            HttpMethod.GET, entity, String.class);

        String expected = "{movieName:DDLJ,moviePrice:\"150\",movieStatus:active}";

        String str = response.getBody().substring(13);
        str = "{".concat(str);

        JSONAssert.assertEquals(expected, str, true);

    }

    @Test
    public void testGetMovieInvalidId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovie/999"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetMovieInvalidId0() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovie/0"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetMovieByTheatreId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovies/1"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testGetMovieByInvalidTheatreId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovies/999"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetMovieByInvalidTheatreId0() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getMovies/0"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetShowtime() {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtTheatreId(1);
        movieTheatreDTO.setMtMovieId(1);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getShowtime"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetShowtimeForNoInvalidId() {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(0);
        movieTheatreDTO.setMtTheatreId(0);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getShowtime"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetShowtimeError() {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(999);
        movieTheatreDTO.setMtTheatreId(999);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getShowtime"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetDiscount() throws JSONException {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(1);
        movieTheatreDTO.setMtTheatreId(1);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getDiscount"),
            HttpMethod.POST, entity, String.class);

        assertEquals("10", response.getBody());
    }

    @Test
    public void testGetDiscountForNoInvalidId() throws JSONException {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtTheatreId(0);
        movieTheatreDTO.setMtTheatreId(0);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getDiscount"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetDiscountForInvalidData() throws JSONException {
        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(999);
        movieTheatreDTO.setMtTheatreId(999);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/getDiscount"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testMovieInsertOperationThroughCSV() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\InsertMovieForIntTest.csv");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieOperationThroughCSV"), HttpMethod.POST, entity,
            String.class);

        assertTrue(response.getBody().contains("true"));

    }

    @Test
    public void testMovieDeleteOperationThroughCSV() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\DeleteMovieForIntTest.csv");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieOperationThroughCSV"), HttpMethod.POST, entity,
            String.class);

        assertTrue(response.getBody().contains("true"));

    }

    @Test
    public void testMovieOperationThroughCSVNoFile() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\DeleteMovieForIntTest.hyut");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieOperationThroughCSV"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testMovieInsertOperationThroughCSVFalseMovie() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\DeleteMovieForIntTestFalseMovie.csv");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieOperationThroughCSV"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testMovieOperationThroughCSVWithNullPath() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(null);

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieOperationThroughCSV"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testMovieOperationThroughCSVWithSpacesPath() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("   ");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieOperationThroughCSV"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testMovieInsertOperationThroughXML() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\InsertMovieForIntTest.xml");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieInsertOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertTrue(response.getBody().contains("true"));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testMovieInsertOperationThroughXMLWithNullPath() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(null);

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieInsertOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testMovieInsertOperationThroughXMLWithSpacesPath() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("   ");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieInsertOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testMovieDeleteOperationThroughXML() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\DeleteMovieForIntTest.xml");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieDeleteOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertTrue(response.getBody().contains("true"));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testMovieDeleteOperationThroughXMLWithNullPath() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(null);

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieDeleteOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testMovieDeleteOperationThroughXMLFalseMovie() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("D:\\DeleteMovieForIntTestFalseMovie.xml");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieDeleteOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testMovieDeleteOperationThroughXMLWithSpacesPath() throws JSONException {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("   ");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/movieInsertOperationThroughXml"), HttpMethod.POST, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    /*
     * @Test public void testWriteMovieSummaryIntoPdf() { ResultDTO resultDTO = new
     * ResultDTO(); resultDTO.setResult("D:\\MoviePDF.pdf"); HttpEntity<ResultDTO>
     * entity = new HttpEntity<>(resultDTO, headers);
     *
     * ResponseEntity<String> response = restTemplate.exchange(
     * createURLWithPort("/mymovieportal/movie/writeMovieSummary"), HttpMethod.POST,
     * entity, String.class);
     *
     * assertTrue(response.getBody().contains("true"));
     *
     * assertEquals(HttpStatus.CREATED, response.getStatusCode()); }
     *
     * @Test public void testWriteMovieSummaryIntoPdfWithSpacesPath() throws
     * JSONException { ResultDTO resultDTO = new ResultDTO();
     * resultDTO.setResult("   ");
     *
     * HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);
     *
     * ResponseEntity<String> response = restTemplate.exchange(
     * createURLWithPort("/mymovieportal/movie/writeMovieSummary"), HttpMethod.POST,
     * entity, String.class);
     *
     * assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
     *
     * }
     *
     * @Test public void testWriteMovieSummaryIntoPdfWithNullPath() throws
     * JSONException { ResultDTO resultDTO = new ResultDTO();
     * resultDTO.setResult(null);
     *
     * HttpEntity<ResultDTO> entity = new HttpEntity<>(resultDTO, headers);
     *
     * ResponseEntity<String> response = restTemplate.exchange(
     * createURLWithPort("/mymovieportal/movie/writeMovieSummary"), HttpMethod.POST,
     * entity, String.class);
     *
     * assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
     *
     * }
     */

    @Test
    public void testWriteMovieSummaryIntoPdf2() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/mymovieportal/movie/downloadPDF"),
            HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testMapMovieTheatre() throws JSONException {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();

        movieTheatreDTO.setMtMovieId(1l);
        movieTheatreDTO.setMtTheatreId(2l);
        movieTheatreDTO.setMtShowtimeId(1l);
        movieTheatreDTO.setDiscount(54);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/mapMovieTheatre"),
            HttpMethod.POST, entity, String.class);

        String expected = "{mtMovieId:1,mtTheatreId:2,mtShowtimeId:1,discount:54}";

        System.out.println(response.getBody());
        String str = response.getBody().substring(21);
        str = "{".concat(str);
        System.out.println(str);
        JSONAssert.assertEquals(expected, str, true);
    }

     @Test
    //@Ignore
    public void testMapMovieTheatreInvalid() throws JSONException {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();

        movieTheatreDTO.setMtMovieId(1l);
        movieTheatreDTO.setMtTheatreId(1l);
        movieTheatreDTO.setMtShowtimeId(1l);
        movieTheatreDTO.setDiscount(10);

        HttpEntity<MovieTheatreDTO> entity = new HttpEntity<>(movieTheatreDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/mapMovieTheatre"),
            HttpMethod.POST, entity, String.class);

        System.out.println(response.getBody());
        System.out.println(response.toString());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void testAddTime() throws JSONException {

        ResultDTO result = new ResultDTO();
        result.setResult("24:00:00");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(result, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/addTime"),
            HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    //@Ignore
    public void testAddTimeError() throws JSONException {

        ResultDTO result = new ResultDTO();
        result.setResult("21:00:00");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(result, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/addTime"),
            HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.toString());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testAddTimeError2() throws JSONException {

        ResultDTO result = new ResultDTO();
        result.setResult("vfvf");

        HttpEntity<ResultDTO> entity = new HttpEntity<>(result, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/movie/addTime"),
            HttpMethod.POST, entity, String.class);


        assertEquals("false", response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
