package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class TheatreDTO.
 */
public class TheatreDTO {

    /** The theatre id. */
    private long theatreId;

    /** The theatre name. */
    private String theatreName;

    /**
     * Instantiates a new theatre DTO.
     */
    public TheatreDTO() {
        super();
        // TODO Auto-generated constructor stub
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (theatreId ^ (theatreId >>> 32));
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
        TheatreDTO other = (TheatreDTO) obj;
        if (theatreId != other.theatreId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TheatreDTO [theatreId=" + theatreId + ", theatreName=" + theatreName + "]";
    }

}
