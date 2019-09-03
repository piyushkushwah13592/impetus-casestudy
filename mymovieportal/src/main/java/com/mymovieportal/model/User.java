package com.mymovieportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity
@Table(name = "user")

public class User {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    /** The name. */
    @Column(name = "user_name")
    private String name;

    /** The last name. */
    @Column(name = "user_lastname")
    private String lastName;

    /** The email. */

    @Column(name = "user_email")
    private String email;

    /** The contact number. */
    @Column(name = "user_contact")
    private String contactNumber;

    /** The password. */
    @Column(name = "user_password")
    private String password;

    /** The user role. */
    @Column(name = "user_role")
    private String userRole;

    /** The user city. */
    @Column(name = "user_city")
    private String userCity;

    /**
     * Instantiates a new user.
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the contact number.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number.
     *
     * @param contact the new contact number
     */
    public void setContactNumber(String contact) {
        this.contactNumber = contact;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user role.
     *
     * @return the user role
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the user role.
     *
     * @param userRole the new user role
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets the user city.
     *
     * @return the user city
     */
    public String getUserCity() {
        return userCity;
    }

    /**
     * Sets the user city.
     *
     * @param userCity the new user city
     */
    public void setUserCity(String userCity) {
        this.userCity = userCity;
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
        result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
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
        User other = (User) obj;
        if (contactNumber == null) {
            if (other.contactNumber != null) {
                return false;
            }
        } else if (!contactNumber.equals(other.contactNumber)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (id != other.id) {
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
        return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", contactNumber="
            + contactNumber + ", password=" + password + ", userRole=" + userRole + ", userCity=" + userCity + "]";
    }

}
