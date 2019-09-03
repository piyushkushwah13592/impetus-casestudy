package com.mymovieportal.service;

import java.util.List;

import com.mymovieportal.dto.SeatBookingDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.SeatBookingException;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.SeatBooking;

// TODO: Auto-generated Javadoc
/**
 * The Interface SeatBookingService.
 */
public interface SeatBookingService {

    /**
     * Gets the seat name.
     *
     * @param seatbooking the seatbooking
     * @return the seat name
     * @throws SeatBookingException the seat booking exception
     */
    List<String> getSeatName(SeatBooking seatbooking) throws SeatBookingException;

    /**
     * Sets the seat name.
     *
     * @param seatbooking the seatbooking
     * @return the seat booking
     * @throws SeatBookingException the seat booking exception
     */
    boolean setSeatName(SeatBooking[] seatbooking) throws SeatBookingException;

    /**
     * Find by sb user id.
     *
     * @param sbUserId the sb user id
     * @return the seat booking[]
     * @throws CityException the city exception
     * @throws UserException the user exception
     */
    SeatBookingDTO[] findBySbUserId(long sbUserId) throws CityException, UserException;

    /**
     * Cancel ticket.
     *
     * @param sbUserId the sb user id
     * @return the seat booking[]
     * @throws CityException the city exception
     * @throws UserException the user exception
     */
    SeatBookingDTO[] cancelTicket(long sbUserId) throws CityException, UserException;

    /**
     * Go to cancel ticket.
     *
     * @param seatBookingId the seat booking id
     * @return the int
     * @throws SeatBookingException the seat booking exception
     */
    int goToCancelTicket(long seatBookingId) throws SeatBookingException;

    /**
     * Gets the seat names.
     *
     * @param sbUserId the sb user id
     * @param sbDate the sb date
     * @param sbShowTime the sb show time
     * @param sbTheatreId the sb theatre id
     * @param sbTheatreId2 the sb theatre id 2
     * @return the seat names
     */
    List<String> getSeatNames(long sbUserId, String sbDate, String sbShowTime, long sbTheatreId, long sbTheatreId2);

}
