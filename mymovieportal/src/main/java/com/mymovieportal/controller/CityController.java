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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mymovieportal.dao.CityDAO;
import com.mymovieportal.dto.CityTheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.model.City;
import com.mymovieportal.service.CityService;

// TODO: Auto-generated Javadoc
/**
 * The Class CityController.
 */
@RestController
@RequestMapping("/mymovieportal/city")
public class CityController {

    /** The city service. */
    @Autowired
    CityService cityService;

    /** The city dao. */
    @Autowired
    CityDAO cityDao;

    /** The logger. */
    Logger logger = LoggerFactory.getLogger(CityController.class);

    /**
     * Gets the.
     *
     * @param cityId
     * the city id
     * @return the response entity
     */
    @RequestMapping(value = "/getCity/{cityId}", method = RequestMethod.GET)
    public ResponseEntity<String> getCity(@PathVariable("cityId") long cityId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        if (cityId <= 0) {
            return new ResponseEntity<>(ExceptionConstants.CITYID, HttpStatus.BAD_REQUEST);
        }
        try {
            City city = cityService.getCity(cityId);
            return new ResponseEntity<>(gson.toJson(city), HttpStatus.CREATED);
        } catch (CityException ex) {
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }

    }

    /**
     * Gets the cities.
     *
     * @return the cities
     */
    @RequestMapping(value = "/getCities", method = RequestMethod.GET)
    public @ResponseBody List<City> getCities() {

        List<City> cityList = cityService.getCities();

        return cityList;
    }

    /**
     * Gets the cities for deletion.
     *
     * @return the cities for deletion
     */
    @RequestMapping(value = "/getCitiesForDeletion", method = RequestMethod.GET)
    public @ResponseBody List<City> getCitiesForDeletion() {

        List<City> cityList = cityService.getCitiesForDeletion();

        return cityList;
    }

    /**
     * Gets the city name only.
     *
     * @param cityId
     * the city id
     * @return the city name only
     */
    // method used for getting city name only
    @RequestMapping(value = "/getCityNameOnly/{cityId}", method = RequestMethod.GET)
    public ResponseEntity<String> getCityNameOnly(@PathVariable("cityId") long cityId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        if (cityId <= 0) {
            return new ResponseEntity<>(ExceptionConstants.CITYID, HttpStatus.BAD_REQUEST);
        }
        try {
            String cityName = cityService.getCityNameOnly(cityId);
            return new ResponseEntity<>(gson.toJson(cityName), HttpStatus.CREATED);
        } catch (CityException ex) {
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Map city theatre.
     *
     * @param cityTheatreDTO the city theatre DTO
     * @return the response entity
     */
    @RequestMapping(value = "/mapCityTheatre", method = RequestMethod.POST)
    public ResponseEntity<String> mapCityTheatre(@RequestBody CityTheatreDTO cityTheatreDTO) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        CityTheatreDTO cityTheatreDTOResponse;
        try {
            cityTheatreDTOResponse = cityService.mapCityTheatre(cityTheatreDTO);
        } catch (CityException ex) {
            // TODO Auto-generated catch block
            logger.warn(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
        return new ResponseEntity<>(gson.toJson(cityTheatreDTOResponse), HttpStatus.CREATED);

    }

}
