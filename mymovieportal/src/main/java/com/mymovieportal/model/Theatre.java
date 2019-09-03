package com.mymovieportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Theatre.
 */
@Entity
@Table(name = "theatre")
public class Theatre {

    /** The theatre id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private long theatreId;

    /** The theatre name. */
    @Column(name = "theatre_name")
    private String theatreName;

    /** The theatre status. */
    @Column(name = "theatre_status")
    private String theatreStatus;

    /**
     * Instantiates a new theatre.
     */
    public Theatre() {
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

    /**
     * Gets the theatre status.
     *
     * @return the theatre status
     */
    public String getTheatreStatus() {
        return theatreStatus;
    }

    /**
     * Sets the theatre status.
     *
     * @param theatreStatus the new theatre status
     */
    public void setTheatreStatus(String theatreStatus) {
        this.theatreStatus = theatreStatus;
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
        result = prime * result + (int) (theatreId ^ (theatreId >>> 32));
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
        Theatre other = (Theatre) obj;
        if (theatreId != other.theatreId) {
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
        return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", theatreStatus=" + theatreStatus
            + "]";
    }

}
