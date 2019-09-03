package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class CityTheatreDTO.
 */
public class CityTheatreDTO {

    /** The ct city id. */
    private long ctCityId;

    /** The ct theatre id. */
    private long ctTheatreId;

    /**
     * Instantiates a new city theatre DTO.
     */
    public CityTheatreDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the ct city id.
     *
     * @return the ct city id
     */
    public long getCtCityId() {
        return ctCityId;
    }

    /**
     * Sets the ct city id.
     *
     * @param ctCityId
     * the new ct city id
     */
    public void setCtCityId(long ctCityId) {
        this.ctCityId = ctCityId;
    }

    /**
     * Gets the ct theatre id.
     *
     * @return the ct theatre id
     */
    public long getCtTheatreId() {
        return ctTheatreId;
    }

    /**
     * Sets the ct theatre id.
     *
     * @param ctTheatreId
     * the new ct theatre id
     */
    public void setCtTheatreId(long ctTheatreId) {
        this.ctTheatreId = ctTheatreId;
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
        result = prime * result + (int) (ctCityId ^ (ctCityId >>> 32));
        result = prime * result + (int) (ctTheatreId ^ (ctTheatreId >>> 32));
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
        CityTheatreDTO other = (CityTheatreDTO) obj;
        if (ctCityId != other.ctCityId) {
            return false;
        }
        if (ctTheatreId != other.ctTheatreId) {
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
        return "CityTheatreDTO [ctCityId=" + ctCityId + ", ctTheatreId=" + ctTheatreId + "]";
    }

}
