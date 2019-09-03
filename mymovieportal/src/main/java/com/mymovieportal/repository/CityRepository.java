package com.mymovieportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mymovieportal.model.City;

// TODO: Auto-generated Javadoc
/**
 * The Interface CityRepository.
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    /**
     * Gets the city ids.
     *
     * @return the city ids
     */
    @Query("select cityId from City")
    List<Long> getCityIds();

    /**
     * Find by city name and city status.
     *
     * @param cityName the city name
     * @param status the status
     * @return the city
     */
    City findByCityNameAndCityStatus(String cityName, String status);

    /**
     * Find by city name.
     *
     * @param cityName the city name
     * @return the city
     */
    City findByCityName(String cityName);
}
