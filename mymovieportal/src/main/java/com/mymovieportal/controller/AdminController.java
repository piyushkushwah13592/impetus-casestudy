package com.mymovieportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mymovieportal.dto.CityDTO;
import com.mymovieportal.dto.MovieDTO;
import com.mymovieportal.dto.TheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.City;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.service.CityService;
import com.mymovieportal.service.MovieService;
import com.mymovieportal.service.TheatreService;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 */
@RestController
@RequestMapping("/mymovieportal/admin")
public class AdminController {

    /** The city service. */
    @Autowired
    CityService cityService;

    /** The theatre service. */
    @Autowired
    TheatreService theatreService;

    /** The movie service. */
    @Autowired
    MovieService movieService;

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * Insert city.
     *
     * @param cityDTO
     * the city DTO
     * @return the response entity
     *
     * @throws CityException
     * SomeException handled
     *
     */
    @RequestMapping(value = "/insertCity", method = RequestMethod.POST)
    public ResponseEntity<String> insertCity(@RequestBody CityDTO cityDTO) throws CityException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        if (cityDTO.getCityName() == null || "".equals(cityDTO.getCityName().trim())) {
            return new ResponseEntity<>(ExceptionConstants.CITYOPERATIONFAILS, HttpStatus.BAD_REQUEST);

        }

        City city = cityService.insertCity(cityDTO);
        return new ResponseEntity<>(gson.toJson(city), HttpStatus.CREATED);
    }

    /**
     * Delete city.
     *
     * @param cityDTO the city DTO
     * @return the response entity
     */
    @RequestMapping(value = "/deleteCity", method = RequestMethod.POST)
    public ResponseEntity<String> deleteCity(@RequestBody CityDTO cityDTO) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        if (cityDTO.getCityName() == null || "".equals(cityDTO.getCityName().trim())) {
            return new ResponseEntity<>(ExceptionConstants.CITYOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }
        try {
            City city = cityService.deleteCity(cityDTO);
            return new ResponseEntity<>(gson.toJson(city), HttpStatus.CREATED);
        } catch (CityException ex) {
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }

    }

    /**
     * Insert theatre.
     *
     * @param theatreDTO
     * the theatre DTO
     * @return the response entity
     * @throws TheatreException
     * SomeException handled
     */
    @RequestMapping(value = "/insertTheatre", method = RequestMethod.POST)
    public ResponseEntity<String> insertTheatre(@RequestBody TheatreDTO theatreDTO) throws TheatreException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        if (theatreDTO.getTheatreName() == null || "".equals(theatreDTO.getTheatreName().trim())) {
            return new ResponseEntity<>(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }
        Theatre theatre = theatreService.insertTheatre(theatreDTO);
        return new ResponseEntity<>(gson.toJson(theatre), HttpStatus.CREATED);
    }

    /**
     * Delete theatre.
     *
     * @param theatreDTO
     * the theatre DTO
     * @return the response entity
     * @throws TheatreException
     * SomeException handled
     */
    @RequestMapping(value = "/deleteTheatre", method = RequestMethod.POST)
    public ResponseEntity<String> deleteTheatre(@RequestBody TheatreDTO theatreDTO) throws TheatreException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        if (theatreDTO.getTheatreName() == null || "".equals(theatreDTO.getTheatreName().trim())) {
            return new ResponseEntity<>(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        try {
            Theatre theatre = theatreService.deleteTheatre(theatreDTO);
            return new ResponseEntity<>(gson.toJson(theatre), HttpStatus.CREATED);
        } catch (TheatreException ex) {
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Insert movie.
     *
     * @param movieDTO
     * the movie DTO
     * @return the response entity
     * @throws MovieException
     * SomeException handled
     */
    @RequestMapping(value = "/insertMovie", method = RequestMethod.POST)
    public ResponseEntity<String> insertMovie(@RequestBody MovieDTO movieDTO) throws MovieException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        if (movieDTO.getMovieName() == null || "".equals(movieDTO.getMovieName().trim())) {
            return new ResponseEntity<>(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }
        Movie movie = movieService.insertMovie(movieDTO);
        return new ResponseEntity<>(gson.toJson(movie), HttpStatus.CREATED);
    }

    /**
     * Delete movie.
     *
     * @param movieDTO
     * the movie DTO
     * @return the response entity
     * @throws MovieException
     * SomeException handled
     */
    @RequestMapping(value = "/deleteMovie", method = RequestMethod.POST)
    public ResponseEntity<String> deleteMovie(@RequestBody MovieDTO movieDTO) throws MovieException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        if (movieDTO.getMovieName() == null || "".equals(movieDTO.getMovieName().trim())) {
            return new ResponseEntity<>(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }
        try {
            Movie movie = movieService.deleteMovie(movieDTO);
            return new ResponseEntity<>(gson.toJson(movie), HttpStatus.CREATED);
        } catch (MovieException ex) {
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

}
