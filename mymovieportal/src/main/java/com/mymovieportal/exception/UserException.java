package com.mymovieportal.exception;

import org.springframework.http.HttpStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class UserException.
 */

public class UserException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new user exception.
     *
     */
    private final HttpStatus status;

    /** The message. */
    private final String message;

    /**
     * Instantiates a new user exception.
     *
     * @param message the message
     */
    // public UserException(final String message) {
    // super(message);
    // }

    /**
     * Instantiates a new user exception.
     *
     * @param message
     * the message
     * @param status
     * the status
     */
    public UserException(final String message, final HttpStatus status) {

        this.message = message;
        this.status = status;

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return "UserException [status=" + status + ", message=" + message + "]";
    }

}
