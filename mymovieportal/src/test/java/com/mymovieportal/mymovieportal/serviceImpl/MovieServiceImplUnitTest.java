package com.mymovieportal.mymovieportal.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.mymovieportal.dto.MovieDTO;
import com.mymovieportal.dto.MovieTheatreDTO;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.MovieTheatre;
import com.mymovieportal.model.Showtime;
import com.mymovieportal.repository.MovieRepository;
import com.mymovieportal.repository.MovieTheatreRepository;
import com.mymovieportal.repository.ShowtimeRepository;
import com.mymovieportal.repository.TheatreRepository;
import com.mymovieportal.service.MovieService;
import com.mymovieportal.service.impl.MovieServiceImpl;

public class MovieServiceImplUnitTest {

    @InjectMocks
    MovieService movieService = new MovieServiceImpl();

    @Mock
    MovieRepository movieRepository;

    @Mock
    TheatreRepository theatreRepository;

    @Mock
    ShowtimeRepository showtimeRepository;

    @Mock
    MovieTheatreRepository movieTheatreRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie());
        movieList.add(new Movie());
        when(movieRepository.findAll()).thenReturn(movieList);
        List<Movie> movies = movieService.getMovies();
        assertNotNull(movies);
    }

    @Test
    public void testGetMovie() throws MovieException {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Dhoom");
        when(movieRepository.findByMovieId(anyLong())).thenReturn(movie);
        Movie movie2 = movieService.getMovie(1);
        assertEquals(movie2.getMovieId(), 1);
    }

    @Test(expected = MovieException.class)
    public void testGetCityInvalid() throws MovieException {
        when(movieRepository.findByMovieId(anyLong())).thenReturn(null);
        Movie movie2 = movieService.getMovie(999);
        assertNull(movie2);
    }

    @Test
    public void testGetMoviesByTheatre() throws TheatreException {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie());
        movieList.add(new Movie());
        List<Long> theatreIds = new ArrayList<>();
        theatreIds.add(1l);
        theatreIds.add(2l);
        when(theatreRepository.getTheatreIds()).thenReturn(theatreIds);
        when(movieRepository.getMoviesByTheatre(anyLong())).thenReturn(movieList);
        List<Movie> movies = movieService.getMoviesByTheatre(1);
        assertNotNull(movies);
    }

    @Test(expected = TheatreException.class)
    public void testGetMoviesByTheatreInvalid() throws TheatreException {
        List<Movie> movieList = new ArrayList<>();
        List<Long> theatreIds = new ArrayList<>();
        theatreIds.add(1l);
        theatreIds.add(2l);
        when(theatreRepository.getTheatreIds()).thenReturn(theatreIds);
        when(movieRepository.getMoviesByTheatre(anyLong())).thenReturn(movieList);
        List<Movie> movies = movieService.getMoviesByTheatre(999);
        assertEquals(movies, null);
    }

    @Test
    public void testGetShowTime() throws MovieException {
        List<String> timeList = new ArrayList<>();
        timeList.add("2018-04-14 19:08:12");
        timeList.add("2018-04-18 13:01:42");
        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(1);
        movieTheatreDTO.setMtTheatreId(1);
        when(showtimeRepository.getShowTime(anyLong(), anyLong())).thenReturn(timeList);
        List<String> times = movieService.getShowTime(movieTheatreDTO);
        assertNotNull(times);
    }

    @Test(expected = MovieException.class)
    public void testGetShowTimeInvalid() throws MovieException {
        List<String> timeList = new ArrayList<>();
        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(1);
        movieTheatreDTO.setMtTheatreId(1);
        when(showtimeRepository.getShowTime(anyLong(), anyLong())).thenReturn(timeList);
        List<String> times = movieService.getShowTime(movieTheatreDTO);
        assertTrue(times.size() == 0);
    }

    @Test
    public void testMovieNameOnly() {
        String movieName = "Dhoom";
        when(movieRepository.getMovieNameOnly(anyLong())).thenReturn(movieName);
        String movieN = movieService.getMovieNameOnly(1);
        assertSame(movieName, movieN);
    }

    @Test
    public void testGetDiscount() throws MovieException {
        List<Integer> discountList = new ArrayList<>();
        discountList.add(10);
        discountList.add(15);
        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(1);
        movieTheatreDTO.setMtTheatreId(1);
        when(movieRepository.getDiscount(anyLong(), anyLong())).thenReturn(discountList);
        int list = movieService.getDiscount(movieTheatreDTO);
        assertNotNull(list);
    }

    /**
     * Test get discount invalid.
     *
     * @throws MovieException the movie exception
     */
    @Test(expected = MovieException.class)
    public void testGetDiscountInvalid() throws MovieException {
        List<Integer> discountList = new ArrayList<>();
        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(999);
        movieTheatreDTO.setMtTheatreId(999);
        when(movieRepository.getDiscount(anyLong(), anyLong())).thenReturn(discountList);
        int list = movieService.getDiscount(movieTheatreDTO);
        assertTrue(list == 0);
    }

    @Test
    public void testInsertMovieForExistingMovie() {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Baba");
        when(movieRepository.findByMovieName(anyString())).thenReturn(movie);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(1);
        movieDTO.setMovieName("Baba");
        Movie movie1 = movieService.insertMovie(movieDTO);
        assertNotNull(movie1);
        assertEquals(movie.getMovieStatus(), "active");
    }

    @Test
    public void testInsertMovieForNonExistingMovie() {
        Movie movie = null;
        when(movieRepository.findByMovieName(anyString())).thenReturn(movie);
        List<Long> movieIds = new ArrayList<>();
        movieIds.add(1l);
        movieIds.add(2l);
        when(movieRepository.getMovieIds()).thenReturn(movieIds);
        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("Bahubali");
        when(movieRepository.save(any(Movie.class))).thenReturn(movie2);
        Movie movie1 = movieService.insertMovie(new MovieDTO());
        assertNotNull(movie1);
        assertEquals(movie1.getMovieName(), "Bahubali");
    }

    @Test
    public void testDeleteMovieForExistingMovie() throws MovieException {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Baba");
        movie.setMovieStatus("active");
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(movie);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(1l);
        movieDTO.setMovieName("Baba");
        Movie movie1 = movieService.deleteMovie(new MovieDTO());
        assertEquals(movie1.getMovieStatus(), "inactive");

    }

    @Test(expected = MovieException.class)
    public void testDeleteMovieForNonExistingMovie() throws MovieException {
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(null);
        Movie movie1 = movieService.deleteMovie(new MovieDTO());
        assertEquals(movie1, null);
    }

    @Test
    public void testAddMovieOperationThroughCSV() throws IOException, MovieException {
        String csvFile = "D://Files-UnitTest//InsertMovieForUnitTest.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String operation = br.readLine();
        List<Movie> mList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("Lagan");
        movie1.setMoviePrice("150");
        movie1.setMovieStatus("inactive");
        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("DHOOM");
        movie2.setMoviePrice("200");
        movie2.setMovieStatus("active");
        Movie movie3 = new Movie();
        movie3.setMovieId(3);
        movie3.setMovieName("DON");
        movie3.setMoviePrice("251");
        movie3.setMovieStatus("active");
        mList.add(movie1);
        mList.add(movie2);
        mList.add(movie3);
        Movie movie = new Movie();
        when(movieRepository.getAllMovies()).thenReturn(mList);
        when(movieRepository.save(movie)).thenReturn(new Movie());
        when(movieRepository.save(new ArrayList<Movie>())).thenReturn(new ArrayList());
        boolean result = movieService.movieOperationThroughCSV(br, operation);
        assertTrue(result);
    }

    @Test
    public void testDeleteMovieOperationThroughCSV() throws IOException, MovieException {
        String csvFile = "D://Files-UnitTest//DeleteMovieForUnitTest.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String operation = br.readLine();
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Baba");
        movie.setMovieStatus("active");
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(movie);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        boolean result = movieService.movieOperationThroughCSV(br, operation);
        assertTrue(result);
    }

    @Test(expected = MovieException.class)
    public void testDeleteMovieOperationThroughCSVForNonExistingMovie() throws IOException, MovieException {
        String csvFile = "D://Files-UnitTest//DeleteMovieForUnitTest.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String operation = br.readLine();
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(null);
        boolean result = movieService.movieOperationThroughCSV(br, operation);
        assertFalse(result);
    }

    @Test(expected = IOException.class)
    public void testDeleteMovieOperationThroughCSVForNonExistingFile() throws IOException, MovieException {
        String csvFile = "F:/DeleteMovieForUnitT.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String operation = br.readLine();
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(null);
        boolean result = movieService.movieOperationThroughCSV(br, operation);
        assertFalse(result);
    }

    @Test
    public void testAddMovieOperationThroughXML() {
        String filePath = "D://Files-UnitTest//InsertMovieForUnitTest.xml";
        File xmlFile = new File(filePath);
        List<Movie> mList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("Lagan");
        movie1.setMoviePrice("150");
        movie1.setMovieStatus("inactive");
        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("DHOOM");
        movie2.setMoviePrice("200");
        movie2.setMovieStatus("active");
        Movie movie3 = new Movie();
        movie3.setMovieId(3);
        movie3.setMovieName("DON");
        movie3.setMoviePrice("251");
        movie3.setMovieStatus("active");
        mList.add(movie1);
        mList.add(movie2);
        mList.add(movie3);
        Movie movie = new Movie();
        when(movieRepository.getAllMovies()).thenReturn(mList);
        when(movieRepository.save(movie)).thenReturn(new Movie());
        when(movieRepository.save(new ArrayList<Movie>())).thenReturn(new ArrayList());
        boolean response = movieService.movieInsertOperationThroughXml(xmlFile);
        assertTrue(response);
    }

    @Test
    public void testDeleteMovieOperationThroughXML() throws MovieException {
        String filePath = "D://Files-UnitTest//DeleteMovieForUnitTest.xml";
        File xmlFile = new File(filePath);
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Baba");
        movie.setMovieStatus("active");
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(movie);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        boolean response = movieService.movieDeleteOperationThroughXml(xmlFile);
        assertTrue(response);

    }

    @Test(expected = MovieException.class)
    public void testDeleteMovieOperationThroughXMLForNonExistingMovie() throws MovieException {
        String filePath = "D://Files-UnitTest//DeleteMovieForUnitTest.xml";
        File xmlFile = new File(filePath);
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(null);
        boolean response = movieService.movieDeleteOperationThroughXml(xmlFile);
        assertFalse(response);

    }

    @Test
    public void testDeleteMovieOperationThroughXMLForNonExistingFile() throws MovieException {
        String filePath = "F:/DeleteMovieForUT.xml";
        File xmlFile = new File(filePath);
        when(movieRepository.findByMovieNameAndMovieStatus(anyString(), anyString())).thenReturn(null);
        boolean response = movieService.movieDeleteOperationThroughXml(xmlFile);
        assertFalse(response);

    }

    @Test
    public void testGetMoviesForDeletion() {

        List<Movie> movieList = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setMovieId(1l);

        Movie movie2 = new Movie();
        movie2.setMovieId(2l);

        movieList.add(movie2);
        movieList.add(movie1);

        when(movieRepository.getMovies()).thenReturn(movieList);

        List<MovieTheatre> movieTheatreList = new ArrayList<>();

        MovieTheatre movieTheatre1 = new MovieTheatre();
        movieTheatre1.setMtMovieId(1l);

        MovieTheatre movieTheatre2 = new MovieTheatre();
        movieTheatre2.setMtMovieId(2l);

        movieTheatreList.add(movieTheatre1);
        movieTheatreList.add(movieTheatre2);

        when(movieTheatreRepository.findAll()).thenReturn(movieTheatreList);

        List<Movie> movieList2 = movieService.getMoviesForDeletion();
        assertNotNull(movieList2);
    }

    @Test
    public void testGetShowtimes() throws ParseException {

        List<Showtime> showtimeList = new ArrayList<>();

        showtimeList.add(new Showtime());
        showtimeList.add(new Showtime());
        showtimeList.add(new Showtime());

        when(showtimeRepository.findAll()).thenReturn(showtimeList);

        showtimeList = movieService.getShowtimes();

        assertNotNull(showtimeList);

    }

    @Test
    public void testMapMovieTheatre() throws ParseException, MovieException {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(1l);
        movieTheatreDTO.setMtTheatreId(1l);
        movieTheatreDTO.setMtShowtimeId(1l);
        movieTheatreDTO.setDiscount(10);

        MovieTheatre movieTheatre = new MovieTheatre();
        when(movieTheatreRepository.save(any(MovieTheatre.class))).thenReturn(movieTheatre);

        MovieTheatreDTO movieTheatreDTO2 = movieService.mapMovieTheatre(movieTheatreDTO);

        assertNotNull(movieTheatreDTO2);

    }

    @Test(expected = MovieException.class)
    public void testMapMovieTheatreError() throws ParseException, MovieException {

        MovieTheatreDTO movieTheatreDTO = new MovieTheatreDTO();
        movieTheatreDTO.setMtMovieId(1l);
        movieTheatreDTO.setMtTheatreId(1l);
        movieTheatreDTO.setMtShowtimeId(1l);
        movieTheatreDTO.setDiscount(10);

        when(movieTheatreRepository.save(any(MovieTheatre.class))).thenThrow(DataIntegrityViolationException.class);

        MovieTheatreDTO movieTheatreDTO2 = movieService.mapMovieTheatre(movieTheatreDTO);

    }

    @Test
    public void testAddTime() throws ParseException, MovieException {

        Showtime showtime = new Showtime();
        when(showtimeRepository.save(any(Showtime.class))).thenReturn(showtime);
        boolean time = movieService.addTime("18:00:00");
        assertTrue(time);
    }

    @Test(expected = MovieException.class)
    public void testAddTimeError() throws ParseException, MovieException {

        Showtime showtime = new Showtime();
        when(showtimeRepository.save(any(Showtime.class))).thenThrow(DataIntegrityViolationException.class);
        boolean time = movieService.addTime("18:00:00");
        assertFalse(time);
    }

    @Test
    public void testAddTimeError2() throws ParseException, MovieException {

        Showtime showtime = new Showtime();
        when(showtimeRepository.save(any(Showtime.class))).thenReturn(showtime);
        boolean time = movieService.addTime("abcd");
        assertFalse(time);
    }
}
