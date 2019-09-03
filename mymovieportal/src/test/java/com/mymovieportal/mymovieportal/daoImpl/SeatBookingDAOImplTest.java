package com.mymovieportal.mymovieportal.daoImpl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mymovieportal.dao.SeatBookingDAO;
import com.mymovieportal.dao.impl.SeatBookingDAOImpl;
import com.mymovieportal.model.SeatBooking;

public class SeatBookingDAOImplTest {

    @InjectMocks
    private SeatBookingDAO seatBookingDAO = new SeatBookingDAOImpl();

    @Mock
    private SessionFactory sessionFactory;

    private Session session;

    private Query query;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        session = mock(Session.class);

        when(sessionFactory.getCurrentSession()).thenReturn(session);

    }

    @Test
    public void testGetSeatName() {

        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(1);
        seatBooking.setSbMovieId(1);
        seatBooking.setSbTheatreId(1);
        seatBooking.setSbShowTime("13:00:00");
        seatBooking.setSbDate("2018-04-26");

        List<SeatBooking> seatBookingList = new ArrayList<>();
        seatBooking.setSeatName("A1");
        seatBookingList.add(seatBooking);

        query = mock(Query.class);
        when(query.list()).thenReturn(seatBookingList);
        when(session.createQuery(anyString())).thenReturn(query);

        List<String> seat = seatBookingDAO.getSeatName(seatBooking);
        assertTrue(seat.size() == 1);

    }

    @Test
    public void testSeatName() {
        SeatBooking[] seatBooking = new SeatBooking[1];
        seatBooking[0] = new SeatBooking();
        seatBooking[0].setSbCityId(1);
        seatBooking[0].setSbMovieId(1);
        seatBooking[0].setSbTheatreId(1);
        seatBooking[0].setSbShowTime("13:00:00");
        seatBooking[0].setSbDate("2018-04-26");
        seatBooking[0].setSeatName("A1");

        boolean flag = seatBookingDAO.setSeatName(seatBooking);
        assertTrue(flag);

    }



    @Test
    public void testGetAllBookedSeats() {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setSbCityId(1);
        seatBooking.setSbMovieId(1);
        seatBooking.setSbTheatreId(1);
        seatBooking.setSbShowTime("13:00:00");
        seatBooking.setSbDate("2018-04-26");
        seatBooking.setSeatName("A1");

        List<SeatBooking> seatBookingList = new ArrayList<>();
        seatBookingList.add(seatBooking);

        query = mock(Query.class);
        when(query.list()).thenReturn(seatBookingList);
        when(session.createQuery(anyString())).thenReturn(query);

        List<SeatBooking> seat = seatBookingDAO.getAllBookedSeat("2018-04-26","21:00:00");
        assertTrue(seat.size() == 1);

    }
}
