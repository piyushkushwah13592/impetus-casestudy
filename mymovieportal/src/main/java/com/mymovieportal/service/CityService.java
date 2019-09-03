package com.mymovieportal.service;

import java.util.List;

import com.mymovieportal.dto.CityDTO;
import com.mymovieportal.dto.CityTheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.model.City;

// TODO: Auto-generated Javadoc
/**
 * The Interface CityService.
 */
public interface CityService {

    /**
     * Gets the city.
     *
     * @param cityId
     * the city id
     * @return the city
     * @throws CityException
     * the city exception
     */
    City getCity(long cityId) throws CityException;

    /**
     * Gets the cities.
     *
     * @return the cities
     */
    List<City> getCities();

    /**
     * Gets the city name only.
     *
     * @param cityId
     * the city id
     * @return the city name only
     * @throws CityException
     * the city exception
     */
    String getCityNameOnly(long cityId) throws CityException;

    /**
     * Delete city.
     *
     * @param cityDTO
     * the city DTO
     * @return the city
     * @throws CityException
     * the city exception
     */
    City deleteCity(CityDTO cityDTO) throws CityException;

    /**
     * Insert city.
     *
     * @param cityDTO
     * the city DTO
     * @return the city
     * @throws CityException
     * the city exception
     */
    City insertCity(CityDTO cityDTO) throws CityException;

    /**
     * Map city theatre.
     *
     * @param theatreDTO the theatre DTO
     * @return the city theatre DTO
     * @throws CityException the city exception
     */
    CityTheatreDTO mapCityTheatre(CityTheatreDTO theatreDTO) throws CityException;

    /**
     * Gets the cities for deletion.
     *
     * @return the cities for deletion
     */
    List<City> getCitiesForDeletion();
}
