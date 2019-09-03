package com.mymovieportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Showtime.
 */
@Entity
@Table(name = "showtime")
public class Showtime {

    /** The show time id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private long showTimeId;

    /** The show time value. */
    @Column(name = "showtime_time")
    private Date showTimeValue;

    /**
     * Instantiates a new showtime.
     */
    public Showtime() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the show time id.
     *
     * @return the show time id
     */
    public long getShowTimeId() {
        return showTimeId;
    }

    /**
     * Sets the show time id.
     *
     * @param showTimeId the new show time id
     */
    public void setShowTimeId(long showTimeId) {
        this.showTimeId = showTimeId;
    }

    /**
     * Gets the show time value.
     *
     * @return the show time value
     */
    public Date getShowTimeValue() {
        return showTimeValue;
    }

    /**
     * Sets the show time value.
     *
     * @param showTimeName the new show time value
     */
    public void setShowTimeValue(Date showTimeName) {
        this.showTimeValue = showTimeName;
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
        result = prime * result + (int) (showTimeId ^ (showTimeId >>> 32));
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
        Showtime other = (Showtime) obj;
        if (showTimeId != other.showTimeId) {
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
        return "Showtime [showTimeId=" + showTimeId + ", showTimeValue=" + showTimeValue + "]";
    }

}
