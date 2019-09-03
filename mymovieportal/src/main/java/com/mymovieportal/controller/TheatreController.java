package com.mymovieportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.service.TheatreService;

// TODO: Auto-generated Javadoc
/**
 * The Class TheatreController.
 */
@RestController
@RequestMapping("/mymovieportal/theatre")
public class TheatreController {

    /** The theatre service impl. */
    @Autowired
    TheatreService theatreService;

    /**
     * Gets the.
     *
     * @param theatreId
     * the theatre id
     * @return the response entity
     * @throws TheatreException
     * SomeException handled
     */
    @RequestMapping(value = "/getTheatre/{theatreId}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("theatreId") long theatreId) throws TheatreException {

        if (theatreId <= 0) {
            return new ResponseEntity<>(ExceptionConstants.THEATREID, HttpStatus.BAD_REQUEST);
        }
        try {
            Theatre theatre = theatreService.getTheatre(theatreId);
            return ResponseEntity.ok().body(new Gson().toJson(theatre));
        } catch (TheatreException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Gets the theatres.
     *
     * @return the theatres
     * @throws TheatreException
     * SomeException handled
     */
    @RequestMapping(value = "/getTheatres", method = RequestMethod.GET)
    public @ResponseBody List<Theatre> getTheatres() throws TheatreException {

        List<Theatre> theatreList = theatreService.getTheatres();
        return theatreList;
    }

    /**
     * Gets the theatres for deletion.
     *
     * @return the theatres for deletion
     * @throws TheatreException the theatre exception
     */
    @RequestMapping(value = "/getTheatresForDeletion", method = RequestMethod.GET)
    public @ResponseBody List<Theatre> getTheatresForDeletion() throws TheatreException {

        List<Theatre> theatreList = theatreService.getTheatresForDeletion();
        return theatreList;
    }

    /**
     * Gets the theatres by city.
     *
     * @param cityId the city id
     * @return the theatres by city
     */
    @RequestMapping(value = "/getTheatres/{cityId}", method = RequestMethod.GET)
    public ResponseEntity<String> getTheatresByCity(@PathVariable("cityId") long cityId) {

        if (cityId <= 0) {
            return new ResponseEntity<>(ExceptionConstants.CITYID, HttpStatus.BAD_REQUEST);
        }
        try {
            List<Theatre> theatreList = theatreService.getTheatresByCity(cityId);
            return ResponseEntity.ok().body(new Gson().toJson(theatreList));
        } catch (CityException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }
}
