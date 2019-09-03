package com.mymovieportal.mymovieportal.dto;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.mymovieportal.dto.CityDTO;
import com.mymovieportal.dto.CityTheatreDTO;
import com.mymovieportal.dto.ConfirmSeatMailDTO;
import com.mymovieportal.dto.LoginDTO;
import com.mymovieportal.dto.MovieDTO;
import com.mymovieportal.dto.MovieTheatreDTO;
import com.mymovieportal.dto.ResultDTO;
import com.mymovieportal.dto.SeatBookingDTO;
import com.mymovieportal.dto.TheatreDTO;
import com.mymovieportal.dto.UserDTO;
import com.mymovieportal.model.SeatBooking;

public class AllDTOUnitTest {

    @InjectMocks
    private SeatBookingDTO seatBookingDTO1 = new SeatBookingDTO();

    @Test
    public void testCityDTO() {
        assertThat(CityDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("cityId"),
            hasValidBeanEqualsFor("cityId"),
            hasValidBeanToString()));
    }

    @Test
    public void testConfirmSeatMailDTO() {
        assertThat(ConfirmSeatMailDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("movieId", "theatreId", "userId", "id"),
            hasValidBeanEqualsFor("movieId", "theatreId", "userId", "id"),
            hasValidBeanToString()));
    }

    @Test
    public void testLoginDTO() {
        assertThat(LoginDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("id"),
            hasValidBeanEqualsFor("id"),
            hasValidBeanToString()));
    }

    @Test
    public void testMovieDTO() {
        assertThat(MovieDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("movieId"),
            hasValidBeanEqualsFor("movieId"),
            hasValidBeanToString()));
    }

    @Test
    public void testMovieTheatreDTO() {
        assertThat(MovieTheatreDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("movieTheatreId", "mtMovieId", "mtShowtimeId", "mtTheatreId"),
            hasValidBeanEqualsFor("movieTheatreId", "mtMovieId", "mtShowtimeId", "mtTheatreId"),
            hasValidBeanToString()));
    }

    @Test
    public void testResultDTO() {
        assertThat(ResultDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("result"),
            hasValidBeanEqualsFor("result"),
            hasValidBeanToString()));
    }

    @Test
    public void testSeatBookingDTO() {
        assertThat(SeatBookingDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("sbCityId", "sbMovieId", "sbTheatreId", "sbUserId", "sbSeatBookingId"),
            hasValidBeanEqualsFor("sbCityId", "sbMovieId", "sbTheatreId", "sbUserId", "sbSeatBookingId"),
            hasValidBeanToString()));
    }

    @Test
    public void testTheatreDTO() {
        assertThat(TheatreDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("theatreId"),
            hasValidBeanEqualsFor("theatreId"),
            hasValidBeanToString()));
    }

    @Test
    public void testUserDTO() {
        assertThat(UserDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("contactNumber", "email"),
            hasValidBeanEqualsFor("contactNumber", "email"),
            hasValidBeanToString()));
    }

    @Test
    public void testCityTheatreDTO() {
        assertThat(CityTheatreDTO.class, allOf(
            hasValidBeanConstructor(),
            hasValidGettersAndSetters(),
            hasValidBeanHashCodeFor("ctCityId", "ctTheatreId"),
            hasValidBeanEqualsFor("ctCityId", "ctTheatreId"),
            hasValidBeanToString()));
    }

    @Test
    public void testGetSeatBookingDTOMapper() {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(1l);
        seatBooking.setSbMovieId(1l);
        seatBooking.setSbTheatreId(1l);
        seatBooking.setSeatName("A1");
        seatBooking.setSbDate("2018-12-16");
        seatBooking.setSbShowTime("18:00:00");
        seatBooking.setSbStatus("booked");
        seatBooking.setSeatBookingId(1l);

        SeatBookingDTO seatBookingDTO = seatBookingDTO1.getSeatBookingDTO(seatBooking);
        assertNotNull(seatBookingDTO);
    }
}
