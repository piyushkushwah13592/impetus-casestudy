package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class CityDTO.
 */
public class CityDTO {

    /** The city id. */
    private long cityId;

    /** The city name. */
    private String cityName;

    /**
     * Instantiates a new city DTO.
     */
    public CityDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (cityId ^ (cityId >>> 32));
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
        CityDTO other = (CityDTO) obj;
        if (cityId != other.cityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CityDTO [cityId=" + cityId + ", cityName=" + cityName + "]";
    }

}
