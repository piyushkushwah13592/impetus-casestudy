package com.mymovieportal.dao;

import java.util.List;

import com.mymovieportal.model.SeatBooking;

// TODO: Auto-generated Javadoc
/**
 * The Interface SeatBookingDAO.
 */
public interface SeatBookingDAO {

    /**
     * Gets the seat name.
     *
     * @param seatbooking the seatbooking
     * @return the seat name
     */
    List<String> getSeatName(SeatBooking seatbooking);

    /**
     * Sets the seat name.
     *
     * @param seatbooking the seatbooking
     * @return the seat booking
     */
    boolean setSeatName(SeatBooking[] seatbooking);

    /**
     * Gets the all booked seat.
     *
     * @param date the date
     * @param time the time
     * @return the all booked seat
     */
    List<SeatBooking> getAllBookedSeat(String date, String time);
}
