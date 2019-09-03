package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDTO.
 */
public class UserDTO {

    /** The name. */
    private String name;

    /** The last name. */
    private String lastName;

    /** The email. */
    private String email;

    /** The contact number. */
    private String contactNumber;

    /** The password. */
    private String password;

    /** The user city. */
    private String userCity;

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
     * @param contactNumber the new contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
        UserDTO other = (UserDTO) obj;
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
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserDTO [name=" + name + ", lastName=" + lastName + ", email=" + email + ", contactNumber="
            + contactNumber + ", password=" + password + ", userCity=" + userCity + "]";
    }

}
