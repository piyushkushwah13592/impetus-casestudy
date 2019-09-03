package com.mymovieportal.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginDTO.
 */
public class LoginDTO {

    /** The id. */
    private long id;

    /** The role. */
    private String role;

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
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        LoginDTO other = (LoginDTO) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoginDTO [id=" + id + ", role=" + role + "]";
    }

}
