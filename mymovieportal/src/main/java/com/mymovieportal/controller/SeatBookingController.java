package com.mymovieportal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mymovieportal.dto.SeatBookingDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.SeatBookingException;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.SeatBooking;
import com.mymovieportal.service.SeatBookingService;

// TODO: Auto-generated Javadoc
/**
 * The Class SeatBookingController.
 */
@RestController
@RequestMapping("/mymovieportal/seatbooking")
public class SeatBookingController {

    /** The seat booking service. */
    @Autowired
    SeatBookingService seatBookingService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Gets the seat name.
     *
     * @param seatBooking the seat booking
     * @return the seat name
     */
    @RequestMapping(value = "/getSeatName", method = RequestMethod.POST)
    public ResponseEntity<String> getSeatName(@RequestBody SeatBooking seatBooking) {

        if (seatBooking.getSbCityId() <= 0 || seatBooking.getSbTheatreId() <= 0
            || seatBooking.getSbMovieId() <= 0) {
            return new ResponseEntity<>(ExceptionConstants.SEATBOOKINGOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        try {
            List<String> seatsName = seatBookingService.getSeatName(seatBooking);
            return ResponseEntity.ok().body(new Gson().toJson(seatsName));
        } catch (SeatBookingException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Sets the seat name.
     *
     * @param seatBooking the seat booking
     * @return the response entity
     */
    @RequestMapping(value = "/setSeatName", method = RequestMethod.POST)
    public ResponseEntity<String> setSeatName(@RequestBody SeatBooking[] seatBooking) {

        if (seatBooking.length <= 0) {
            return new ResponseEntity<>(ExceptionConstants.SEATBOOKINGOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        boolean result;
        try {
            result = seatBookingService.setSeatName(seatBooking);
        } catch (SeatBookingException ex) {
            // TODO Auto-generated catch block
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
        return ResponseEntity.ok().body(new Gson().toJson(result));

    }

    /**
     * Gets the user history.
     *
     * @param sbUserId
     * the sb user id
     * @return the user history
     * @throws SeatBookingException
     * SomeException handled
     */
    @RequestMapping(value = "/getUserHistory/{sbUserId}", method = RequestMethod.GET)
    public ResponseEntity<String> getUserHistory(@PathVariable("sbUserId") long sbUserId) throws SeatBookingException {

        if (sbUserId < 0) {
            return new ResponseEntity<>(ExceptionConstants.USERID, HttpStatus.BAD_REQUEST);
        }

        try {
            SeatBookingDTO[] seatBooking = seatBookingService.findBySbUserId(sbUserId);
            return ResponseEntity.ok().body(new Gson().toJson(seatBooking));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        } catch (CityException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Cancel ticket.
     *
     * @param sbUserId the sb user id
     * @return the response entity
     */
    @RequestMapping(value = "/cancelTicket/{sbUserId}", method = RequestMethod.GET)
    public ResponseEntity<String> cancelTicket(@PathVariable("sbUserId") long sbUserId) {

        if (sbUserId < 0) {
            return new ResponseEntity<>(ExceptionConstants.SEATBOOKINGOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        try {
            SeatBookingDTO[] seatBooking = seatBookingService.cancelTicket(sbUserId);
            return ResponseEntity.ok().body(new Gson().toJson(seatBooking));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        } catch (CityException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Go to cancel ticket.
     *
     * @param seatBookingId the seat booking id
     * @return the response entity
     */
    @RequestMapping(value = "/goToCancelTicket/{seatBookingId}", method = RequestMethod.GET)
    public ResponseEntity<String> goToCancelTicket(@PathVariable("seatBookingId") long seatBookingId) {

        if (seatBookingId < 0) {
            return new ResponseEntity<>(ExceptionConstants.SEATBOOKINGOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }
        try {
            int seatBooking = seatBookingService.goToCancelTicket(seatBookingId);
            return ResponseEntity.ok().body(new Gson().toJson(seatBooking));
        } catch (SeatBookingException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }

    }
}
