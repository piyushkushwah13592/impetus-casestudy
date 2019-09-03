package com.mymovieportal.dao;

import java.util.List;

import com.mymovieportal.model.City;

// TODO: Auto-generated Javadoc
/**
 * The Interface CityDAO.
 */
public interface CityDAO {

    /**
     * Gets the city.
     *
     * @param cityId the city id
     * @return the city
     */
    City getCity(long cityId);

    /**
     * Gets the cities.
     *
     * @return the cities
     */
    List<City> getCities();

    /**
     * Gets the only city.
     *
     * @param cityId the city id
     * @return the city name only
     */
    String getCityNameOnly(long cityId);
}
