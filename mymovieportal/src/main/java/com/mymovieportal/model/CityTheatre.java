package com.mymovieportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class CityTheatre.
 */
@Entity
@Table(name = "citytheatre")
public class CityTheatre {

    /** The city theatre id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citytheatre_id")
    private Long cityTheatreId;

    /** The ct theatre id. */
    @Column(name = "ct_theatre_id")
    private long ctTheatreId;

    /** The ct city id. */
    @Column(name = "ct_city_id")
    private long ctCityId;

    /**
     * Gets the city theatre id.
     *
     * @return the city theatre id
     */
    public Long getCityTheatreId() {
        return cityTheatreId;
    }

    /**
     * Sets the city theatre id.
     *
     * @param cityTheatreId the new city theatre id
     */
    public void setCityTheatreId(Long cityTheatreId) {
        this.cityTheatreId = cityTheatreId;
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
     * @param ctTheatreId the new ct theatre id
     */
    public void setCtTheatreId(long ctTheatreId) {
        this.ctTheatreId = ctTheatreId;
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
     * @param ctCityId the new ct city id
     */
    public void setCtCityId(long ctCityId) {
        this.ctCityId = ctCityId;
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
        result = prime * result + ((cityTheatreId == null) ? 0 : cityTheatreId.hashCode());
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
        CityTheatre other = (CityTheatre) obj;
        if (cityTheatreId == null) {
            if (other.cityTheatreId != null) {
                return false;
            }
        } else if (!cityTheatreId.equals(other.cityTheatreId)) {
            return false;
        }
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
        return "CityTheatre [cityTheatreId=" + cityTheatreId + ", ctTheatreId=" + ctTheatreId + ", ctCityId=" + ctCityId
            + "]";
    }

}
