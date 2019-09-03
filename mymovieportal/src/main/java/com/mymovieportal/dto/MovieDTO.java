package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieDTO.
 */
public class MovieDTO {

    /** The movie id. */
    private long movieId;

    /** The movie name. */
    private String movieName;

    /**
     * Instantiates a new movie DTO.
     */
    public MovieDTO() {
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
        MovieDTO other = (MovieDTO) obj;
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
        return "MovieDTO [movieId=" + movieId + ", movieName=" + movieName + "]";
    }

}
