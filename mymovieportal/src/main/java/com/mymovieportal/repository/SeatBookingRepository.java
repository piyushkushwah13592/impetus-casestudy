package com.mymovieportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mymovieportal.model.SeatBooking;

// TODO: Auto-generated Javadoc
/**
 * The Interface SeatBookingRepository.
 */
public interface SeatBookingRepository extends JpaRepository<SeatBooking, Integer> {

    /**
     * Find by sb user id.
     *
     * @param sbUserId
     * the sb user id
     * @return the seat booking[]
     */
    SeatBooking[] findBySbUserId(long sbUserId);

    /*
     * @Query("Select sb from SeatBooking sb where sbUserId = :userid and sbDate >= :date "
     * + "and sbShowTime > :time and sbStatus='booked'") SeatBooking[]
     * cancelTicket(long userid, String date, String time);
     */

    /**
     * Cancel ticket.
     *
     * @param userid
     * the userid
     * @param date
     * the date
     * @param time
     * the time
     * @return the seat booking[]
     */
    @Query(value = "Select * from SeatBooking  where sb_user_id = :userid and sb_date = :date "
        + "and sb_showtime > :time and status='booked'" + "union "
        + "Select * from SeatBooking  where sb_user_id = :userid and sb_date > :date and status='booked'",
        nativeQuery = true)
    SeatBooking[] cancelTicket(@Param("userid") long userid, @Param("date") String date, @Param("time") String time);

    /**
     * Go to cancel ticket.
     *
     * @param seatbookingid
     * the seatbookingid
     * @return the int
     */
    @Modifying
    @Query("update SeatBooking set sbStatus='cancel' where seatBookingId=:seatbookingid")
    int goToCancelTicket(@Param("seatbookingid") long seatbookingid);

    /**
     * Gets the seat names.
     *
     * @param userId
     * the user id
     * @param date
     * the date
     * @param time
     * the time
     * @param theatreId
     * the theatre id
     * @param movieId
     * the movie id
     * @return the seat names
     */
    @Query("Select seatName from SeatBooking where sbUserId = :userId and sbDate = :date and sbShowTime = :time and "
        + "sbTheatreId = :theatreId and sbMovieId = :movieId and" + " status ='booked' ")
    List<String> getSeatNames(long userId, String date, String time, long theatreId, long movieId);

    /**
     * Gets the all user ids.
     *
     * @return the all user ids
     */
    @Query("select id from User")
    List<Long> getAllUserIds();

    /**
     * Gets the all seat booking ids.
     *
     * @return the all seat booking ids
     */
    @Query("select seatBookingId from SeatBooking")
    List<Long> getAllSeatBookingIds();

}
