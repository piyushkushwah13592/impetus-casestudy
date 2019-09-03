package com.mymovieportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class City.
 */
@Entity
@Table(name = "city")
public class City {

    /** The city id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private long cityId;

    /** The city name. */
    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_status")
    private String cityStatus;

    /**
     * Gets the city id.
     *
     * @return the city id
     */
    public long getCityId() {
        return cityId;
    }

    /**
     * Sets the city id.
     *
     * @param cityId the new city id
     */
    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    /**
     * Gets the city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the city name.
     *
     * @param cityName the new city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityStatus() {
        return cityStatus;
    }

    public void setCityStatus(String cityStatus) {
        this.cityStatus = cityStatus;
    }

    /**
     * Instantiates a new city.
     */
    public City() {
        super();
        // TODO Auto-generated constructor stub
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
        result = prime * result + (int) (cityId ^ (cityId >>> 32));
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
        City other = (City) obj;
        if (cityId != other.cityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "City [cityId=" + cityId + ", cityName=" + cityName + ", cityStatus=" + cityStatus + "]";
    }

}
