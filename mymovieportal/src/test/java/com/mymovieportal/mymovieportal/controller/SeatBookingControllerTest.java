package com.mymovieportal.mymovieportal.controller;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
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
import com.mymovieportal.model.SeatBooking;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MymovieportalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeatBookingControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetSeatName() throws JSONException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(1);
        seatBooking.setSbMovieId(1);
        seatBooking.setSbTheatreId(1);
        seatBooking.setSbDate("2018-12-16");
        seatBooking.setSbShowTime("21:00:00");
        HttpEntity<SeatBooking> entity = new HttpEntity<>(seatBooking, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/getSeatName"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetSeatNameInvalidId0() throws JSONException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(0);
        seatBooking.setSbMovieId(0);
        seatBooking.setSbTheatreId(0);
        seatBooking.setSbDate("2018-05-18");
        seatBooking.setSbShowTime("10:53:38");

        HttpEntity<SeatBooking> entity = new HttpEntity<>(seatBooking, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/getSeatName"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetSeatNameInvalidId() throws JSONException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(999);
        seatBooking.setSbMovieId(999);
        seatBooking.setSbTheatreId(999);
        seatBooking.setSbDate("2018-05-18");
        seatBooking.setSbShowTime("10:53:38");

        HttpEntity<SeatBooking> entity = new HttpEntity<>(seatBooking, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/getSeatName"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testSetSeatName() throws JSONException {
        SeatBooking[] seatBooking = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSbShowTime("21:00:00");
        seatBooking[0].setSeatName("Z3");
        seatBooking[0].setSbDate("2018-07-17");
        seatBooking[0].setSbUserId(1);

        HttpEntity<SeatBooking[]> entity = new HttpEntity<>(seatBooking, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/setSeatName"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testSetSeatNameInValidData() throws JSONException {
        SeatBooking[] seatBooking = new SeatBooking[0];
        HttpEntity<SeatBooking[]> entity = new HttpEntity<>(seatBooking, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/setSeatName"), HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGetUserHistory() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/getUserHistory/1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetUserHistoryInvalidId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/getUserHistory/999"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetUserHistoryInvalidId2() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/getUserHistory/-1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testCancelTicket() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/cancelTicket/1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testCancelTicketInvalidId() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/cancelTicket/999"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testCancelTicketInvalidId2() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/cancelTicket/-1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testGoToCancelTicket() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/goToCancelTicket/1"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGoToCancelTicketInvalid() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/goToCancelTicket/999"), HttpMethod.GET, entity,
            String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGoToCancelTicketInvalid2() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/mymovieportal/seatbooking/goToCancelTicket/-1"), HttpMethod.GET, entity,
            String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
