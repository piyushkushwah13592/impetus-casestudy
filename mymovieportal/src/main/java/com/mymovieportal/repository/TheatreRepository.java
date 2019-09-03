package com.mymovieportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mymovieportal.model.Theatre;

// TODO: Auto-generated Javadoc
/**
 * The Interface TheatreRepository.
 */
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

    /**
     * Find by theatre id.
     *
     * @param theatreId
     * the theatre id
     * @return the theatre
     */
    Theatre findByTheatreId(long theatreId);

    /**
     * Gets the theatres.
     *
     * @return the theatres
     */
    @Query("from Theatre where theatreStatus = 'active' ")
    List<Theatre> getTheatres();

    /**
     * Gets the theatres by city.
     *
     * @param cityId
     * the city id
     * @return the theatres by city
     */
    @Query("from Theatre where theatreStatus='active' and theatreId in"
        + "(select ctTheatreId from CityTheatre where ctCityId=:cityId)")
    List<Theatre> getTheatresByCity(@Param("cityId") long cityId);

    /**
     * Gets the theatre name only.
     *
     * @param theatreid
     * the theatreid
     * @return the theatre name only
     */
    @Query("select theatreName from Theatre where theatreId= :theatreid")
    String getTheatreNameOnly(@Param("theatreid") long theatreid);

    /**
     * Find by theatre name and theatre status.
     *
     * @param theatreName
     * the theatre name
     * @param status
     * the status
     * @return the theatre
     */
    Theatre findByTheatreNameAndTheatreStatus(String theatreName, String status);

    /**
     * Gets the theatre ids.
     *
     * @return the theatre ids
     */
    @Query("select theatreId from Theatre")
    List<Long> getTheatreIds();

    /**
     * Find by theatre name.
     *
     * @param theatreName
     * the theatre name
     * @return the theatre
     */
    Theatre findByTheatreName(String theatreName);
}
