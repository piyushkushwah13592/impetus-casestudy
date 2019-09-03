package com.mymovieportal.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfirmSeatMailDTO.
 */
public class ConfirmSeatMailDTO {

    /** The id. */
    private long id;

    /** The date. */
    private String date;

    /** The total seats. */
    private int totalSeats;

    /** The time. */
    private String time;

    /** The seat names. */
    private List<String> seatNames;

    /** The theatre id. */
    private long theatreId;

    /** The movie id. */
    private long movieId;

    /** The user id. */
    private Long userId;

    /** The theatre name. */
    private String theatreName;

    /** The movie name. */
    private String movieName;

    /**
     * Gets the theatre name.
     *
     * @return the theatre name
     */
    public String getTheatreName() {
        return theatreName;
    }

    /**
     * Sets the theatre name.
     *
     * @param theatreName the new theatre name
     */
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    /**
     * Gets the movie name.
     *
     * @return the movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets the movie name.
     *
     * @param movieName the new movie name
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the total seats.
     *
     * @return the total seats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * Sets the total seats.
     *
     * @param totalSeats the new total seats
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * Gets the seat names.
     *
     * @return the seat names
     */
    public List<String> getSeatNames() {
        return seatNames;
    }

    /**
     * Sets the seat names.
     *
     * @param seatNames the new seat names
     */
    public void setSeatNames(List<String> seatNames) {
        this.seatNames = seatNames;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time.
     *
     * @param time the new time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the theatre id.
     *
     * @return the theatre id
     */
    public long getTheatreId() {
        return theatreId;
    }

    /**
     * Sets the theatre id.
     *
     * @param theatreId the new theatre id
     */
    public void setTheatreId(long theatreId) {
        this.theatreId = theatreId;
    }

    /**
     * Gets the movie id.
     *
     * @return the movie id
     */
    public long getMovieId() {
        return movieId;
    }

    /**
     * Sets the movie id.
     *
     * @param movieId the new movie id
     */
    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (movieId ^ (movieId >>> 32));
        result = prime * result + (int) (theatreId ^ (theatreId >>> 32));
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConfirmSeatMailDTO other = (ConfirmSeatMailDTO) obj;
        if (id != other.id) {
            return false;
        }
        if (movieId != other.movieId) {
            return false;
        }
        if (theatreId != other.theatreId) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConfirmSeatMailDTO [id=" + id + ", date=" + date + ", totalSeats=" + totalSeats + ", time=" + time
            + ", seatNames=" + seatNames + ", theatreId=" + theatreId + ", movieId=" + movieId + ", userId=" + userId
            + ", theatreName=" + theatreName + ", movieName=" + movieName + "]";
    }

}
