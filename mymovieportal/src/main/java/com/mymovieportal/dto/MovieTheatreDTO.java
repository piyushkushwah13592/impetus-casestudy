package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieTheatreDTO.
 */
public class MovieTheatreDTO {

    /** The movie theatre id. */
    private Long movieTheatreId;

    /** The mt movie id. */
    private long mtMovieId;

    /** The mt theatre id. */
    private long mtTheatreId;

    /** The mt showtime id. */
    private long mtShowtimeId;

    /** The mt discount. */
    private int discount;

    
    public MovieTheatreDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Gets the movie theatre id.
     *
     * @return the movie theatre id
     */
    public Long getMovieTheatreId() {
        return movieTheatreId;
    }

    /**
     * Sets the movie theatre id.
     *
     * @param movieTheatreId the new movie theatre id
     */
    public void setMovieTheatreId(Long movieTheatreId) {
        this.movieTheatreId = movieTheatreId;
    }

    /**
     * Gets the mt movie id.
     *
     * @return the mt movie id
     */
    public long getMtMovieId() {
        return mtMovieId;
    }

    /**
     * Sets the mt movie id.
     *
     * @param mtMovieId the new mt movie id
     */
    public void setMtMovieId(long mtMovieId) {
        this.mtMovieId = mtMovieId;
    }

    /**
     * Gets the mt theatre id.
     *
     * @return the mt theatre id
     */
    public long getMtTheatreId() {
        return mtTheatreId;
    }

    /**
     * Sets the mt theatre id.
     *
     * @param mtTheatreId the new mt theatre id
     */
    public void setMtTheatreId(long mtTheatreId) {
        this.mtTheatreId = mtTheatreId;
    }

    /**
     * Gets the mt showtime id.
     *
     * @return the mt showtime id
     */
    public long getMtShowtimeId() {
        return mtShowtimeId;
    }

    /**
     * Sets the mt showtime id.
     *
     * @param mtShowtimeId the new mt showtime id
     */
    public void setMtShowtimeId(long mtShowtimeId) {
        this.mtShowtimeId = mtShowtimeId;
    }

    /**
     * Gets the mt discount.
     *
     * @return the mt discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets the mt discount.
     *
     * @param mtDiscount the new mt discount
     */
    public void setDiscount(int mtDiscount) {
        this.discount = mtDiscount;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieTheatreId == null) ? 0 : movieTheatreId.hashCode());
		result = prime * result + (int) (mtMovieId ^ (mtMovieId >>> 32));
		result = prime * result + (int) (mtShowtimeId ^ (mtShowtimeId >>> 32));
		result = prime * result + (int) (mtTheatreId ^ (mtTheatreId >>> 32));
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieTheatreDTO other = (MovieTheatreDTO) obj;
		if (movieTheatreId == null) {
			if (other.movieTheatreId != null)
				return false;
		} else if (!movieTheatreId.equals(other.movieTheatreId))
			return false;
		if (mtMovieId != other.mtMovieId)
			return false;
		if (mtShowtimeId != other.mtShowtimeId)
			return false;
		if (mtTheatreId != other.mtTheatreId)
			return false;
		return true;
	}

    @Override
	public String toString() {
		return "MovieTheatreDTO [movieTheatreId=" + movieTheatreId + ", mtMovieId=" + mtMovieId + ", mtTheatreId="
				+ mtTheatreId + ", mtShowtimeId=" + mtShowtimeId + ", discount=" + discount + "]";
	}

}
