package com.mymovieportal.mymovieportal.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.mymovieportal.model.City;
import com.mymovieportal.model.CityTheatre;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.MovieTheatre;
import com.mymovieportal.model.SeatBooking;
import com.mymovieportal.model.Showtime;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.model.User;

public class AllModelUnitTest {

    @Test
    public void testCity() {
        assertThat(City.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("cityId"),
            hasValidBeanEqualsFor("cityId"),
            hasValidBeanToString()));
    }

    @Test
    public void testCityTheatre() {
        assertThat(CityTheatre.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("cityTheatreId", "ctCityId", "ctTheatreId"),
            hasValidBeanEqualsFor("cityTheatreId", "ctCityId", "ctTheatreId"),
            hasValidBeanToString()));
    }

    @Test
    public void testMovie() {
        assertThat(Movie.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("movieId"),
            hasValidBeanEqualsFor("movieId"),
            hasValidBeanToString()));
    }

    @Test
    public void testMovieTheatre() {
        assertThat(MovieTheatre.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("movieTheatreId", "mtMovieId", "mtShowtimeId", "mtTheatreId"),
            hasValidBeanEqualsFor("movieTheatreId", "mtMovieId", "mtShowtimeId", "mtTheatreId"),
            hasValidBeanToString()));
    }

    @Test
    public void testSeatBooking() {
        assertThat(SeatBooking.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("sbCityId", "sbMovieId", "sbTheatreId", "sbUserId"),
            hasValidBeanEqualsFor("sbCityId", "sbMovieId", "sbTheatreId", "sbUserId"),
            hasValidBeanToString()));
    }

    @Test
    public void testShowtime() {
        assertThat(Showtime.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("showTimeId"),
            hasValidBeanEqualsFor("showTimeId"),
            hasValidBeanToString()));
    }

    @Test
    public void testTheatre() {
        assertThat(Theatre.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("theatreId"),
            hasValidBeanEqualsFor("theatreId"),
            hasValidBeanToString()));
    }

    @Test
    public void testUser() {
        assertThat(User.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("contactNumber", "email", "id"),
            hasValidBeanEqualsFor("contactNumber", "email", "id"),
            hasValidBeanToString()));
    }
}
