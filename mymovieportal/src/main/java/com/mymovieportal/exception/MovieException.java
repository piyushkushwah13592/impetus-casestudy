package com.mymovieportal.exception;

import org.springframework.http.HttpStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieException.
 */
public class MovieException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The status. */
    private final HttpStatus status;

    /** The message. */
    private final String message;

    /**
     * Instantiates a new movie exception.
     *
     * @param message
     * the message
     */
    // public MovieException(final String message) {
    // super(message);
    // }

    /**
     * Instantiates a new movie exception.
     *
     * @param message the message
     * @param status the status
     */
    public MovieException(final String message, final HttpStatus status) {

        this.message = message;
        this.status = status;

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
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return message;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return "MovieException [status=" + status + ", message=" + message + "]";
    }

}
