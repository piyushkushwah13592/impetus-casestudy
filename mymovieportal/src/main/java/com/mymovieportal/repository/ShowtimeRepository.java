package com.mymovieportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mymovieportal.model.Showtime;

// TODO: Auto-generated Javadoc
/**
 * The Interface ShowtimeRepository.
 */
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    /**
     * Gets the show time.
     *
     * @param movieId
     * the movie id
     * @param theatreId
     * the theatre id
     * @return the show time
     */
    @Query("select  DATE_FORMAT(showTimeValue, '%d-%m-%y %H:%i:%s') from Showtime where showTimeId in"
        + "(select mtShowtimeId from MovieTheatre where mtMovieId=:movieId and mtTheatreId=:theatreId)")
    List<String> getShowTime(@Param("movieId") long movieId, @Param("theatreId") long theatreId);

}
