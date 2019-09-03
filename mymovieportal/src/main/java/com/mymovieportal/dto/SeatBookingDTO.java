package com.mymovieportal.dto;

import com.mymovieportal.model.SeatBooking;

// TODO: Auto-generated Javadoc
/**
 * The Class SeatBookingDTO.
 */
public class SeatBookingDTO {

    /** The sb city id. */
    private long sbCityId;

    /** The sb theatre id. */
    private long sbTheatreId;

    /** The sb movie id. */
    private long sbMovieId;

    /** The sb showtime id. */
    private String sbShowTime;

    /** The seat name. */
    private String seatName;

    /** The sb date. */
    private String sbDate;

    /** The sb status. */
    private String sbStatus;

    /** The sb user id. */
    private long sbUserId;

    /** The sb city name. */
    private String sbCityName;

    /** The sb theatre name. */
    private String sbTheatreName;

    /** The sb movie name. */
    private String sbMovieName;

    /** The sb seat booking id. */
    private Long sbSeatBookingId;

    /**
     * Instantiates a new seat booking DTO.
     */
    public SeatBookingDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the sb city id.
     *
     * @return the sb city id
     */
    public long getSbCityId() {
        return sbCityId;
    }

    /**
     * Sets the sb city id.
     *
     * @param sbCityId the new sb city id
     */
    public void setSbCityId(long sbCityId) {
        this.sbCityId = sbCityId;
    }

    /**
     * Gets the sb theatre id.
     *
     * @return the sb theatre id
     */
    public long getSbTheatreId() {
        return sbTheatreId;
    }

    /**
     * Sets the sb theatre id.
     *
     * @param sbTheatreId the new sb theatre id
     */
    public void setSbTheatreId(long sbTheatreId) {
        this.sbTheatreId = sbTheatreId;
    }

    /**
     * Gets the sb movie id.
     *
     * @return the sb movie id
     */
    public long getSbMovieId() {
        return sbMovieId;
    }

    /**
     * Sets the sb movie id.
     *
     * @param sbMovieId the new sb movie id
     */
    public void setSbMovieId(long sbMovieId) {
        this.sbMovieId = sbMovieId;
    }

    /**
     * Gets the sb showtime id.
     *
     * @return the sb showtime id
     */
    public String getSbShowTime() {
        return sbShowTime;
    }

    /**
     * Sets the sb showtime id.
     *
     * @param sbShowTime the new sb show time
     */
    public void setSbShowTime(String sbShowTime) {
        this.sbShowTime = sbShowTime;
    }

    /**
     * Gets the seat name.
     *
     * @return the seat name
     */
    public String getSeatName() {
        return seatName;
    }

    /**
     * Sets the seat name.
     *
     * @param seatName the new seat name
     */
    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    /**
     * Gets the sb date.
     *
     * @return the sb date
     */
    public String getSbDate() {
        return sbDate;
    }

    /**
     * Sets the sb date.
     *
     * @param sbDate the new sb date
     */
    public void setSbDate(String sbDate) {
        this.sbDate = sbDate;
    }

    /**
     * Gets the sb status.
     *
     * @return the sb status
     */
    public String getSbStatus() {
        return sbStatus;
    }

    /**
     * Sets the sb status.
     *
     * @param sbStatus the new sb status
     */
    public void setSbStatus(String sbStatus) {
        this.sbStatus = sbStatus;
    }

    /**
     * Gets the sb user id.
     *
     * @return the sb user id
     */
    public long getSbUserId() {
        return sbUserId;
    }

    /**
     * Sets the sb user id.
     *
     * @param sbUserId the new sb user id
     */
    public void setSbUserId(long sbUserId) {
        this.sbUserId = sbUserId;
    }

    /**
     * Gets the sb city name.
     *
     * @return the sb city name
     */
    public String getSbCityName() {
        return sbCityName;
    }

    /**
     * Sets the sb city name.
     *
     * @param sbCityName the new sb city name
     */
    public void setSbCityName(String sbCityName) {
        this.sbCityName = sbCityName;
    }

    /**
     * Gets the sb theatre name.
     *
     * @return the sb theatre name
     */
    public String getSbTheatreName() {
        return sbTheatreName;
    }

    /**
     * Sets the sb theatre name.
     *
     * @param sbTheatreName the new sb theatre name
     */
    public void setSbTheatreName(String sbTheatreName) {
        this.sbTheatreName = sbTheatreName;
    }

    /**
     * Gets the sb movie name.
     *
     * @return the sb movie name
     */
    public String getSbMovieName() {
        return sbMovieName;
    }

    /**
     * Sets the sb movie name.
     *
     * @param sbMovieName the new sb movie name
     */
    public void setSbMovieName(String sbMovieName) {
        this.sbMovieName = sbMovieName;
    }

    /**
     * Gets the sb seat booking id.
     *
     * @return the sb seat booking id
     */
    public Long getSbSeatBookingId() {
        return sbSeatBookingId;
    }

    /**
     * Sets the sb seat booking id.
     *
     * @param sbSeatBookingId the new sb seat booking id
     */
    public void setSbSeatBookingId(Long sbSeatBookingId) {
        this.sbSeatBookingId = sbSeatBookingId;
    }

    /**
     * Gets the seat booking DTO.
     *
     * @param seatBooking the seat booking
     * @return the seat booking DTO
     */
    public SeatBookingDTO getSeatBookingDTO(SeatBooking seatBooking) {
        SeatBookingDTO seatBookingDTO = new SeatBookingDTO();
        seatBookingDTO.setSbCityId(seatBooking.getSbCityId());
        seatBookingDTO.setSbMovieId(seatBooking.getSbMovieId());
        seatBookingDTO.setSbTheatreId(seatBooking.getSbTheatreId());
        seatBookingDTO.setSeatName(seatBooking.getSeatName());
        seatBookingDTO.setSbDate(seatBooking.getSbDate());
        seatBookingDTO.setSbShowTime(seatBooking.getSbShowTime());
        seatBookingDTO.setSbStatus(seatBooking.getSbStatus());
        seatBookingDTO.setSbSeatBookingId(seatBooking.getSeatBookingId());
        return seatBookingDTO;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (sbCityId ^ (sbCityId >>> 32));
        result = prime * result + (int) (sbMovieId ^ (sbMovieId >>> 32));
        result = prime * result + ((sbSeatBookingId == null) ? 0 : sbSeatBookingId.hashCode());
        result = prime * result + (int) (sbTheatreId ^ (sbTheatreId >>> 32));
        result = prime * result + (int) (sbUserId ^ (sbUserId >>> 32));
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
        SeatBookingDTO other = (SeatBookingDTO) obj;
        if (sbCityId != other.sbCityId) {
            return false;
        }
        if (sbMovieId != other.sbMovieId) {
            return false;
        }
        if (sbSeatBookingId == null) {
            if (other.sbSeatBookingId != null) {
                return false;
            }
        } else if (!sbSeatBookingId.equals(other.sbSeatBookingId)) {
            return false;
        }
        if (sbTheatreId != other.sbTheatreId) {
            return false;
        }
        if (sbUserId != other.sbUserId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SeatBookingDTO [sbCityId=" + sbCityId + ", sbTheatreId=" + sbTheatreId + ", sbMovieId=" + sbMovieId
            + ", sbShowTime=" + sbShowTime + ", seatName=" + seatName + ", sbDate=" + sbDate + ", sbStatus=" + sbStatus
            + ", sbUserId=" + sbUserId + ", sbCityName=" + sbCityName + ", sbTheatreName=" + sbTheatreName
            + ", sbMovieName=" + sbMovieName + ", sbSeatBookingId=" + sbSeatBookingId + "]";
    }

}
