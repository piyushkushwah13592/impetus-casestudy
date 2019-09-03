package com.mymovieportal.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
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
import com.mymovieportal.dto.MovieTheatreDTO;
import com.mymovieportal.dto.ResultDTO;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.Showtime;
import com.mymovieportal.service.MovieService;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieController.
 */
@RestController
@RequestMapping("/mymovieportal/movie")
public class MovieController {

	/** The movie service impl. */
	@Autowired
	MovieService movieService;

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Gets the movies.
	 *
	 * @return the movies
	 * @throws MovieException
	 *             SomeException handled
	 */
	@RequestMapping(value = "/getMovies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> getMovies() throws MovieException {

		List<Movie> movieList = movieService.getMovies();
		return movieList;
	}

	/**
	 * Gets the movies for deletion.
	 *
	 * @return the movies for deletion
	 * @throws MovieException
	 *             the movie exception
	 */
	@RequestMapping(value = "/getMoviesForDeletion", method = RequestMethod.GET)
	public @ResponseBody List<Movie> getMoviesForDeletion() throws MovieException {

		List<Movie> movieList = movieService.getMoviesForDeletion();
		return movieList;
	}

	/**
	 * Gets the movie.
	 *
	 * @param movieId
	 *            the movie id
	 * @return the movie
	 */
	@RequestMapping(value = "/getMovie/{movieId}", method = RequestMethod.GET)
	public ResponseEntity<String> getMovie(@PathVariable("movieId") long movieId) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		if (movieId <= 0) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEID, HttpStatus.BAD_REQUEST);
		}
		try {
			Movie movie = movieService.getMovie(movieId);
			return new ResponseEntity<>(gson.toJson(movie), HttpStatus.CREATED);
		} catch (MovieException ex) {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		}
	}

	/**
	 * Gets the movie by theatre.
	 *
	 * @param theatreId
	 *            the theatre id
	 * @return the movie by theatre
	 */
	@RequestMapping(value = "/getMovies/{theatreId}", method = RequestMethod.GET)
	public ResponseEntity<String> getMovieByTheatre(@PathVariable("theatreId") long theatreId) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		if (theatreId <= 0) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEID, HttpStatus.BAD_REQUEST);
		}

		try {
			List<Movie> movieList = movieService.getMoviesByTheatre(theatreId);
			return new ResponseEntity<>(gson.toJson(movieList), HttpStatus.CREATED);

		} catch (TheatreException ex) {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		}

	}

	/**
	 * Gets the password.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the show time
	 */
	@RequestMapping(value = "/getShowtime", method = RequestMethod.POST)
	public ResponseEntity<String> getShowTime(@RequestBody MovieTheatreDTO movieTheatreDTO) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

		if (movieTheatreDTO.getMtMovieId() <= 0 || movieTheatreDTO.getMtTheatreId() <= 0) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.BAD_REQUEST);
		}

		try {
			List<String> timeList = movieService.getShowTime(movieTheatreDTO);
			return new ResponseEntity<>(gson.toJson(timeList), HttpStatus.CREATED);
		} catch (MovieException ex) {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		}
	}

	/**
	 * Gets the discount.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the discount
	 */
	@RequestMapping(value = "/getDiscount", method = RequestMethod.POST)
	public ResponseEntity<String> getDiscount(@RequestBody MovieTheatreDTO movieTheatreDTO) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

		if (movieTheatreDTO.getMtMovieId() <= 0 || movieTheatreDTO.getMtTheatreId() <= 0) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.BAD_REQUEST);
		}

		try {
			int discount = movieService.getDiscount(movieTheatreDTO);
			return new ResponseEntity<>(gson.toJson(discount), HttpStatus.CREATED);
		} catch (MovieException ex) {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		}
	}

	/**
	 * Movie operation through CSV.
	 *
	 * @param resultDTO
	 *            the result DTO
	 * @return the response entity
	 */
	@RequestMapping(value = "/movieOperationThroughCSV", method = RequestMethod.POST)
	public ResponseEntity<String> movieOperationThroughCSV(@RequestBody ResultDTO resultDTO) {
		boolean response = false;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		if (resultDTO.getResult() == null || "".equals(resultDTO.getResult().trim())) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.BAD_REQUEST);
		}

		try {

			String csvFile = resultDTO.getResult();
			InputStream in = new FileInputStream(csvFile);
			Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(reader);

			String operation = br.readLine();
			response = movieService.movieOperationThroughCSV(br, operation);
			return new ResponseEntity<>(gson.toJson(response), HttpStatus.CREATED);
		} catch (MovieException ex) {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		} catch (IOException ex) {
			logger.warn(ex.getMessage());
			return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Movie insert operation through xml.
	 *
	 * @param resultDTO
	 *            the result DTO
	 * @return the response entity
	 */
	@RequestMapping(value = "/movieInsertOperationThroughXml", method = RequestMethod.POST)
	public ResponseEntity<String> movieInsertOperationThroughXml(@RequestBody ResultDTO resultDTO) {

		if (resultDTO.getResult() == null || "".equals(resultDTO.getResult().trim())) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.BAD_REQUEST);
		}

		boolean response = false;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		String filePath = resultDTO.getResult();
		File xmlFile = new File(filePath);

		response = movieService.movieInsertOperationThroughXml(xmlFile);
		return new ResponseEntity<>(gson.toJson(response), HttpStatus.CREATED);

	}

	/**
	 * Movie delete operation through xml.
	 *
	 * @param resultDTO
	 *            the result DTO
	 * @return the response entity
	 */
	@RequestMapping(value = "/movieDeleteOperationThroughXml", method = RequestMethod.POST)
	public ResponseEntity<String> movieDeleteOperationThroughXml(@RequestBody ResultDTO resultDTO) {

		if (resultDTO.getResult() == null || "".equals(resultDTO.getResult().trim())) {
			return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.BAD_REQUEST);
		}

		boolean response = false;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		try {
			String filePath = resultDTO.getResult();
			File xmlFile = new File(filePath);

			response = movieService.movieDeleteOperationThroughXml(xmlFile);
			return new ResponseEntity<>(gson.toJson(response), HttpStatus.CREATED);

		} catch (MovieException ex) {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());

		}
	}

	/**
	 * Write movie summary into pdf.
	 *
	 * @return the boolean
	 */
	/*
	 * @RequestMapping(value = "/writeMovieSummary", method = RequestMethod.POST)
	 * public ResponseEntity<String> writeMovieSummaryIntoPdf(@RequestBody ResultDTO
	 * resultDTO) {
	 *
	 * if (resultDTO.getResult() == null || "".equals(resultDTO.getResult().trim()))
	 * { return new ResponseEntity<>(ExceptionConstants.MOVIEOPERATIONFAILS,
	 * HttpStatus.BAD_REQUEST); } Document document = new Document(); Boolean status
	 * = false; try { String filePath = resultDTO.getResult(); PdfWriter writer =
	 * PdfWriter.getInstance(document, new FileOutputStream(filePath));
	 * document.open(); String movieSummary = ""; List<Movie> movieList =
	 * movieService.getMovies(); for (Movie movie : movieList) { movieSummary =
	 * movieSummary .concat(movie.getMovieName() + " " + movie.getMoviePrice() + " "
	 * + movie.getMovieStatus() + "\n"); }
	 *
	 * document.add(new Paragraph(movieSummary)); document.close(); writer.close();
	 * status = true; } catch (DocumentException | FileNotFoundException e) {
	 * logger.error(e.getMessage()); } Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create(); return
	 * new ResponseEntity<>(gson.toJson(status), HttpStatus.CREATED); }
	 */
	/*
	 * @RequestMapping(value = "/getPdfData", method = RequestMethod.GET) public
	 * ResponseEntity<String> getPdfData() {
	 *
	 * PdfReader reader;
	 *
	 * try {
	 *
	 * reader = new PdfReader("D:\\MovieSummary.pdf");
	 *
	 * // pageNumber = 1 String textFromPage =
	 * PdfTextExtractor.getTextFromPage(reader, 1);
	 *
	 * System.out.println(textFromPage + "**************");
	 *
	 * reader.close();
	 *
	 * } catch (IOException e) { e.printStackTrace(); } Gson gson = new
	 * GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create(); return
	 * new ResponseEntity<>(gson.toJson(true), HttpStatus.CREATED); }
	 */

	/**
	 * Write movie summary into pdf 2.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ResponseEntity<String> writeMovieSummaryIntoPdf2() {

		String movieSummary = "";
		List<Movie> movieList = movieService.getMovies();
		for (Movie movie : movieList) {
			movieSummary = movieSummary
					.concat(movie.getMovieName() + " " + movie.getMoviePrice() + " " + movie.getMovieStatus() + "\n");
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		return new ResponseEntity<>(gson.toJson(movieSummary), HttpStatus.CREATED);
	}

	/**
	 * Gets the showtimes.
	 *
	 * @return the showtimes
	 * @throws MovieException
	 *             the movie exception
	 * @throws ParseException
	 *             the parse exception
	 */
	@RequestMapping(value = "/getShowtimes", method = RequestMethod.GET)
	public @ResponseBody List<Showtime> getShowtimes() throws MovieException, ParseException {

		List<Showtime> showTimeList = movieService.getShowtimes();
		return showTimeList;
	}

	/**
	 * Map movie theatre.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the response entity
	 */
	@RequestMapping(value = "/mapMovieTheatre", method = RequestMethod.POST)
	public ResponseEntity<String> mapMovieTheatre(@RequestBody MovieTheatreDTO movieTheatreDTO) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

		MovieTheatreDTO dto;
		try {
			dto = movieService.mapMovieTheatre(movieTheatreDTO);
		} catch (MovieException ex) {
			// TODO Auto-generated catch block
			logger.warn(ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		}

		return new ResponseEntity<>(gson.toJson(dto), HttpStatus.CREATED);

	}

	/**
	 * Adds the time.
	 *
	 * @param result
	 *            the result
	 * @return the response entity
	 * @throws ParseException
	 *             the parse exception
	 */
	@RequestMapping(value = "/addTime", method = RequestMethod.POST)
	public ResponseEntity<String> addTime(@RequestBody ResultDTO result) throws ParseException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

		boolean flag;

		try {
			flag = movieService.addTime(result.getResult());
		} catch (MovieException ex) {
			// TODO Auto-generated catch block
			logger.warn(ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		}

		return new ResponseEntity<>(gson.toJson(flag), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/downloadMovieTheatrePDF", method = RequestMethod.GET)
	public ResponseEntity<String> movieTheatrePdf() throws ParseException {

		String movieSummary = movieService.getMovieTheatrePdf();

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		return new ResponseEntity<>(gson.toJson(movieSummary), HttpStatus.CREATED);
	}
}
