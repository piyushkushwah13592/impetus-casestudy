package com.mymovieportal.service;

import java.io.BufferedReader;
import java.io.File;
import java.text.ParseException;
import java.util.List;

import com.mymovieportal.dto.MovieDTO;
import com.mymovieportal.dto.MovieTheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.Showtime;

// TODO: Auto-generated Javadoc
/**
 * The Interface MovieService.
 */
public interface MovieService {

	/**
	 * Gets the movies.
	 *
	 * @return the movies
	 */
	List<Movie> getMovies();

	/**
	 * Gets the movie name only.
	 *
	 * @param movieId
	 *            the movie id
	 * @return the movie name only
	 */
	String getMovieNameOnly(long movieId);

	/**
	 * Gets the show time.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the show time
	 * @throws MovieException
	 *             the movie exception
	 */
	List<String> getShowTime(MovieTheatreDTO movieTheatreDTO) throws MovieException;

	/**
	 * Gets the movies by theatre.
	 *
	 * @param theatreId
	 *            the theatre id
	 * @return the movies by theatre
	 * @throws TheatreException
	 *             the theatre exception
	 */
	List<Movie> getMoviesByTheatre(long theatreId) throws TheatreException;

	/**
	 * Gets the movie.
	 *
	 * @param movieId
	 *            the movie id
	 * @return the movie
	 * @throws MovieException
	 *             the movie exception
	 */
	Movie getMovie(long movieId) throws MovieException;

	/**
	 * Gets the discount.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the discount
	 * @throws MovieException
	 *             the movie exception
	 */
	int getDiscount(MovieTheatreDTO movieTheatreDTO) throws MovieException;

	/**
	 * Delete movie.
	 *
	 * @param movieDTO
	 *            the movie DTO
	 * @return the movie
	 * @throws MovieException
	 *             the movie exception
	 */
	Movie deleteMovie(MovieDTO movieDTO) throws MovieException;

	/**
	 * Insert movie.
	 *
	 * @param movieDTO
	 *            the movie DTO
	 * @return the movie
	 */
	Movie insertMovie(MovieDTO movieDTO);

	/**
	 * Movie operation through CSV.
	 *
	 * @param br
	 *            the br
	 * @param operation
	 *            the operation
	 * @return true, if successful
	 * @throws MovieException
	 *             the movie exception
	 */
	boolean movieOperationThroughCSV(BufferedReader br, String operation) throws MovieException;

	/**
	 * Movie insert operation through xml.
	 *
	 * @param br
	 *            the br
	 * @return true, if successful
	 */
	boolean movieInsertOperationThroughXml(File br);

	/**
	 * Movie delete operation through xml.
	 *
	 * @param br
	 *            the br
	 * @return true, if successful
	 * @throws MovieException
	 *             the movie exception
	 */
	boolean movieDeleteOperationThroughXml(File br) throws MovieException;

	/**
	 * Gets the showtimes.
	 *
	 * @return the showtimes
	 * @throws ParseException
	 *             the parse exception
	 */
	List<Showtime> getShowtimes() throws ParseException;

	/**
	 * Map movie theatre.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the movie theatre DTO
	 * @throws MovieException
	 *             the movie exception
	 * @throws CityException
	 */
	MovieTheatreDTO mapMovieTheatre(MovieTheatreDTO movieTheatreDTO) throws MovieException;

	/**
	 * Adds the time.
	 *
	 * @param result
	 *            the result
	 * @return true, if successful
	 * @throws ParseException
	 *             the parse exception
	 * @throws MovieException
	 *             the movie exception
	 */
	boolean addTime(String result) throws ParseException, MovieException;

	/**
	 * Gets the movies for deletion.
	 *
	 * @return the movies for deletion
	 */
	List<Movie> getMoviesForDeletion();

	String getMovieTheatrePdf() throws ParseException;

}
