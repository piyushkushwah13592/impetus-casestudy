package com.mymovieportal.mymovieportal.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.mymovieportal.dao.SeatBookingDAO;
import com.mymovieportal.dto.SeatBookingDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.SeatBookingException;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.SeatBooking;
import com.mymovieportal.repository.MovieRepository;
import com.mymovieportal.repository.SeatBookingRepository;
import com.mymovieportal.repository.TheatreRepository;
import com.mymovieportal.service.CityService;
import com.mymovieportal.service.SeatBookingService;
import com.mymovieportal.service.impl.SeatBookingServiceImpl;

public class SeatBookingServiceImplUnitTest {

    @InjectMocks
    SeatBookingService seatBookingService = new SeatBookingServiceImpl();

    @Mock
    SeatBookingDAO seatBookingDao;

    @Mock
    SeatBookingRepository seatBookingRepository;

    @Mock
    CityService cityService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    TheatreRepository theatreRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSeatsName() throws SeatBookingException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(1);
        seatBooking.setSbMovieId(1);
        seatBooking.setSbTheatreId(1);
        seatBooking.setSbDate("2018-04-19");

        List<String> seatNames = new ArrayList<>();
        seatNames.add("A6");
        seatNames.add("B7");
        when(seatBookingDao.getSeatName(seatBooking)).thenReturn(seatNames);
        seatNames = seatBookingService.getSeatName(seatBooking);
        assertNotNull(seatNames);
    }

    @Test(expected = SeatBookingException.class)
    public void testGetSeatsNameInvalid() throws SeatBookingException {
        SeatBooking seatBooking = new SeatBooking();
        List<String> seatNames = new ArrayList<>();
        when(seatBookingDao.getSeatName(seatBooking)).thenReturn(seatNames);
        seatNames = seatBookingService.getSeatName(seatBooking);
        assertEquals(0, seatNames.size());
    }

    @Test(expected = SeatBookingException.class)
    public void testSetSeatName() throws SeatBookingException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbDate("2018-04-19");
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSeatName("A1");
        seatBooking[0].setSbShowTime("18:00:00");;
        when(seatBookingDao.setSeatName(seatBooking)).thenReturn(true);
        boolean flag = seatBookingService.setSeatName(seatBooking);
        assertTrue(flag);

        SeatBooking seatBooking2[] = new SeatBooking[1];
        seatBooking2[0] = new SeatBooking();
        seatBooking2[0].setSbCityId(1);
        seatBooking2[0].setSbDate("2018-04-19");
        seatBooking2[0].setSbMovieId(1);
        seatBooking2[0].setSbTheatreId(1);
        seatBooking2[0].setSeatName("A1");
        seatBooking2[0].setSbShowTime("18:00:00");;

        when(seatBookingDao.setSeatName(seatBooking)).thenThrow(DataIntegrityViolationException.class);
        flag = seatBookingService.setSeatName(seatBooking);

    }

    @Test
    public void testSetSeatNameError() throws SeatBookingException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbDate("2018-04-19");
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSeatName("A1");
        when(seatBookingDao.setSeatName(seatBooking)).thenReturn(true);
        boolean flag = seatBookingService.setSeatName(seatBooking);
        assertTrue(flag);
    }

    @Test
    public void testFindBySbUserId() throws CityException, UserException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSbUserId(1L);
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbDate("2018-04-19");
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSeatName("A1");
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        when(seatBookingRepository.getAllUserIds()).thenReturn(userIds);
        when(seatBookingRepository.findBySbUserId(1L)).thenReturn(seatBooking);
        when(cityService.getCityNameOnly(seatBooking[0].getSbCityId())).thenReturn("Indore");
        when(movieRepository.getMovieNameOnly(seatBooking[0].getSbMovieId())).thenReturn("Badshah");
        when(theatreRepository.getTheatreNameOnly(seatBooking[0].getSbTheatreId())).thenReturn("INOX");
        SeatBookingDTO s[] = seatBookingService.findBySbUserId(1L);
        assertTrue(s[0] != null);

    }

    @Test(expected = UserException.class)
    public void testFindBySbUserIdInvalid() throws CityException, UserException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        when(seatBookingRepository.getAllUserIds()).thenReturn(userIds);
        when(seatBookingRepository.findBySbUserId(anyLong())).thenReturn(seatBooking);
        SeatBookingDTO s[] = seatBookingService.findBySbUserId(0L);
        assertNull(s);
    }

    @Test(expected = CityException.class)
    public void testFindBySbUserIdInvalidCityId() throws CityException, UserException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSbUserId(1L);
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbDate("2018-04-19");
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSeatName("A1");
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        when(seatBookingRepository.getAllUserIds()).thenReturn(userIds);
        when(seatBookingRepository.findBySbUserId(1L)).thenReturn(seatBooking);
        when(cityService.getCityNameOnly(seatBooking[0].getSbCityId())).thenThrow(CityException.class);
        when(movieRepository.getMovieNameOnly(seatBooking[0].getSbMovieId())).thenReturn("Badshah");
        when(theatreRepository.getTheatreNameOnly(seatBooking[0].getSbTheatreId())).thenReturn("INOX");
        SeatBookingDTO s[] = seatBookingService.findBySbUserId(1L);
        assertNull(s[0]);

    }

    @Test
    public void testCancelTicket() throws CityException, UserException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSeatBookingId(1L);
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbDate("2018-04-19");
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSeatName("A1");
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        when(seatBookingRepository.getAllUserIds()).thenReturn(userIds);
        when(seatBookingRepository.cancelTicket(anyLong(), anyString(), anyString())).thenReturn(seatBooking);
        when(cityService.getCityNameOnly(seatBooking[0].getSbCityId())).thenReturn("Indore");
        when(movieRepository.getMovieNameOnly(seatBooking[0].getSbMovieId())).thenReturn("Badshah");
        when(theatreRepository.getTheatreNameOnly(seatBooking[0].getSbTheatreId())).thenReturn("INOX");
        SeatBookingDTO s[] = seatBookingService.cancelTicket(1L);
        assertTrue(s[0] != null);
    }

    @Test(expected = CityException.class)
    public void testCancelTicketInvalidCityId() throws CityException, UserException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSeatBookingId(1L);
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbDate("2018-04-19");
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSeatName("A1");
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        when(cityService.getCityNameOnly(seatBooking[0].getSbCityId())).thenThrow(CityException.class);
        when(seatBookingRepository.getAllUserIds()).thenReturn(userIds);
        when(seatBookingRepository.cancelTicket(anyLong(), anyString(), anyString())).thenReturn(seatBooking);

        when(movieRepository.getMovieNameOnly(seatBooking[0].getSbMovieId())).thenReturn("Badshah");
        when(theatreRepository.getTheatreNameOnly(seatBooking[0].getSbTheatreId())).thenReturn("INOX");
        SeatBookingDTO s[] = seatBookingService.cancelTicket(1L);
        assertNull(s[0]);
    }

    @Test(expected = UserException.class)
    public void testCancelTicketInvalid() throws CityException, UserException {
        SeatBooking seatBooking[] = new SeatBooking[1];
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        when(seatBookingRepository.getAllUserIds()).thenReturn(userIds);
        when(seatBookingRepository.cancelTicket(90L, null, null)).thenReturn(seatBooking);
        when(cityService.getCityNameOnly(999)).thenReturn(null);
        when(movieRepository.getMovieNameOnly(999)).thenReturn(null);
        when(theatreRepository.getTheatreNameOnly(999)).thenReturn(null);
        SeatBookingDTO s[] = seatBookingService.cancelTicket(0L);
        assertNull(s);
    }

    @Test
    public void testGoToCancelTicket() throws SeatBookingException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSeatBookingId(1L);
        seatBooking.setSbCityId(1);
        seatBooking.setSbDate("2018-04-19");
        seatBooking.setSbTheatreId(1);
        seatBooking.setSbMovieId(1);
        seatBooking.setSeatName("A2");
        List<Long> seatBookingIds = new ArrayList<>();
        seatBookingIds.add(1L);
        seatBookingIds.add(2L);
        when(seatBookingRepository.getAllSeatBookingIds()).thenReturn(seatBookingIds);
        when(seatBookingRepository.goToCancelTicket(anyLong())).thenReturn(1);
        int count = seatBookingService.goToCancelTicket(1L);
        assertTrue(count > 0);
    }

    @Test(expected = SeatBookingException.class)
    public void testGoToCancelTicketInvalid() throws SeatBookingException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSeatBookingId(1L);
        seatBooking.setSbCityId(1);
        seatBooking.setSbDate("2018-04-19");
        seatBooking.setSbTheatreId(1);
        seatBooking.setSbMovieId(1);
        seatBooking.setSeatName("A2");
        List<Long> seatBookingIds = new ArrayList<>();
        seatBookingIds.add(1L);
        seatBookingIds.add(2L);
        when(seatBookingRepository.getAllSeatBookingIds()).thenReturn(seatBookingIds);
        when(seatBookingRepository.goToCancelTicket(seatBooking.getSeatBookingId())).thenReturn(1);
        int count = seatBookingService.goToCancelTicket(0L);
        assertTrue(count == 0);
    }

    @Test
    public void testGetSeatNames() {
        List<String> seatNames = new ArrayList<>();
        seatNames.add("A2");
        seatNames.add("B7");
        when(seatBookingRepository.getSeatNames(anyLong(), anyString(), anyString(), anyLong(), anyLong()))
            .thenReturn(seatNames);
        List<String> seatList = seatBookingService.getSeatNames(1L, "2018-09018", "18:00:00", 1, 1);
        assertTrue(seatList != null);
    }

    @Test
    public void testGetSeatNamesInvalid() {
        List<String> seatNames = new ArrayList<>();
        seatNames.add("A2");
        seatNames.add("B7");
        when(seatBookingRepository.getSeatNames(anyLong(), anyString(), anyString(), anyLong(), anyLong()))
            .thenReturn(null);
        List<String> seatList = seatBookingService.getSeatNames(1L, "", "", 999, 999);
        assertTrue(seatList == null);

    }
}
