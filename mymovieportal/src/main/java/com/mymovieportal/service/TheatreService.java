package com.mymovieportal.service;

import java.util.List;

import com.mymovieportal.dto.TheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.Theatre;

// TODO: Auto-generated Javadoc
/**
 * The Interface TheatreService.
 */
public interface TheatreService {

    /**
     * Gets the theatre.
     *
     * @param theatreId the theatre id
     * @return the theatre
     * @throws TheatreException the theatre exception
     */
    Theatre getTheatre(long theatreId) throws TheatreException;

    /**
     * Gets the theatres.
     *
     * @return the theatres
     */
    List<Theatre> getTheatres();

    /**
     * Gets the theare name only.
     *
     * @param theatreId the theatre id
     * @return the theare name only
     */
    String getTheareNameOnly(long theatreId);

    /**
     * Gets the theatres by city.
     *
     * @param cityId the city id
     * @return the theatres by city
     * @throws CityException the city exception
     */
    List<Theatre> getTheatresByCity(long cityId) throws CityException;

    /**
     * Delete theatre.
     *
     * @param theatreDTO the theatre DTO
     * @return the theatre
     * @throws TheatreException the theatre exception
     */
    Theatre deleteTheatre(TheatreDTO theatreDTO) throws TheatreException;

    /**
     * Insert theatre.
     *
     * @param theatreDTO the theatre DTO
     * @return the theatre
     */
    Theatre insertTheatre(TheatreDTO theatreDTO);

    /**
     * Gets the theatres for deletion.
     *
     * @return the theatres for deletion
     */
    List<Theatre> getTheatresForDeletion();

}
