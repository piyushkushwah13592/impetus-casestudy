package com.mymovieportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mymovieportal.model.Movie;

// TODO: Auto-generated Javadoc
/**
 * The Interface MovieRepository.
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    /**
     * Gets the movies.
     *
     * @return the movies
     */
    @Query("from Movie where movieStatus = 'active' ")
    List<Movie> getMovies();

    /**
     * Gets the movies by theatre.
     *
     * @param theatreId
     * the theatre id
     * @return the movies by theatre
     */
    @Query("from Movie where movieStatus = 'active' and movieId in (select mtMovieId from MovieTheatre where "
        + "mtTheatreId=:theatreId)")
    List<Movie> getMoviesByTheatre(@Param("theatreId") long theatreId);

    /**
     * Find by movie id.
     *
     * @param movieId
     * the movie id
     * @return the movie
     */
    Movie findByMovieId(long movieId);

    /**
     * Gets the movie name only.
     *
     * @param movieid
     * the movieid
     * @return the movie name only
     */
    @Query("select movieName from Movie where movieId=:movieid")
    String getMovieNameOnly(@Param("movieid") long movieid);

    /**
     * Gets the discount.
     *
     * @param movieId
     * the movie id
     * @param theatreId
     * the theatre id
     * @return the discount
     */
    @Query("select discount from MovieTheatre where mtMovieId=:movieId and mtTheatreId=:theatreId")
    List<Integer> getDiscount(@Param("movieId") long movieId, @Param("theatreId") long theatreId);

    /**
     * Gets the movie ids.
     *
     * @return the movie ids
     */
    @Query("select movieId from Movie")
    List<Long> getMovieIds();

    /**
     * Find by movie name and movie status.
     *
     * @param movieName
     * the movie name
     * @param status
     * the status
     * @return the movie
     */
    Movie findByMovieNameAndMovieStatus(String movieName, String status);

    /**
     * Find by movie name.
     *
     * @param movieName
     * the movie name
     * @return the movie
     */
    Movie findByMovieName(String movieName);

    /**
     * Gets the all movies.
     *
     * @return the all movies
     */
    @Query("from Movie")
    List<Movie> getAllMovies();
}
