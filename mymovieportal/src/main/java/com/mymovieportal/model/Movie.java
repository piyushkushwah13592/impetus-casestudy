package com.mymovieportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Movie.
 */
@Entity
@Table(name = "movie")
public class Movie {

    /** The movie id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private long movieId;

    /** The movie name. */
    @Column(name = "movie_name")
    private String movieName;

    /** The movie price. */
    @Column(name = "movie_price")
    private String moviePrice;

    /** The movie status. */
    @Column(name = "movie_status")
    private String movieStatus;

    /**
     * Instantiates a new movie.
     */
    public Movie() {
        super();
        // TODO Auto-generated constructor stub
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
     * Gets the movie price.
     *
     * @return the movie price
     */
    public String getMoviePrice() {
        return moviePrice;
    }

    /**
     * Sets the movie price.
     *
     * @param moviePrice the new movie price
     */
    public void setMoviePrice(String moviePrice) {
        this.moviePrice = moviePrice;
    }

    /**
     * Gets the movie status.
     *
     * @return the movie status
     */
    public String getMovieStatus() {
        return movieStatus;
    }

    /**
     * Sets the movie status.
     *
     * @param movieStatus the new movie status
     */
    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (movieId ^ (movieId >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        Movie other = (Movie) obj;
        if (movieId != other.movieId) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", moviePrice=" + moviePrice
            + ", movieStatus=" + movieStatus + "]";
    }

}
