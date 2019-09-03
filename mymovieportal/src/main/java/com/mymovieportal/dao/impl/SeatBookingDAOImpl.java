package com.mymovieportal.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mymovieportal.dao.SeatBookingDAO;
import com.mymovieportal.model.SeatBooking;

// TODO: Auto-generated Javadoc
/**
 * The Class SeatBookingDAOImpl.
 */
@Repository
@Transactional
public class SeatBookingDAOImpl implements SeatBookingDAO {

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Gets the session.
     *
     * @return the session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.dao.SeatBookingDAO#getSeatName(com.mymovieportal.model.
     * SeatBooking)
     */
    @Override
    public List<String> getSeatName(SeatBooking seatbooking) {
        // TODO Auto-generated method stub

        long sbCityId = seatbooking.getSbCityId();
        long sbTheatreId = seatbooking.getSbTheatreId();
        long sbMovieId = seatbooking.getSbMovieId();
        String sbShowTime = seatbooking.getSbShowTime();
        String sbDate = seatbooking.getSbDate();

        String myquery =
            "from SeatBooking where sbCityId=:cityId and sbTheatreId=:theatreId and sbMovieId=:movieId and "
                + "sbShowTime=:showtime and sbDate=:date and sbStatus = 'booked'";
        Query query = getSession().createQuery(myquery);
        query.setParameter("cityId", sbCityId);
        query.setParameter("theatreId", sbTheatreId);
        query.setParameter("movieId", sbMovieId);
        query.setParameter("showtime", sbShowTime);
        query.setParameter("date", sbDate);

        List<SeatBooking> seatList = query.list();
        Iterator<SeatBooking> seatItr = seatList.iterator();
        List<String> seatsName = new ArrayList();
        while (seatItr.hasNext()) {
            SeatBooking seat = seatItr.next();
            seatsName.add(seat.getSeatName());
        }
        return seatsName;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.dao.SeatBookingDAO#setSeatName(com.mymovieportal.model.
     * SeatBooking)
     */
    @Override
    public boolean setSeatName(SeatBooking[] seatbooking) {
        // TODO Auto-generated method stub
        for (int i = 0; i < seatbooking.length; i++) {

            seatbooking[i].setSbStatus("booked");
            // TODO Auto-generated method stub
            getSession().persist(seatbooking[i]);

        }
        return true;
    }

    @Override
    public List<SeatBooking> getAllBookedSeat(String date, String time) {
        String myquery = "from SeatBooking where sbShowTime > :showtime and sbDate=:date and sbStatus = 'booked'";
        Query query = getSession().createQuery(myquery);

        query.setParameter("showtime", time);
        query.setParameter("date", date);
        return query.list();

    }
}
