package com.mymovieportal.exception;

import org.springframework.http.HttpStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class MailException.
 */
public class MailException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The status. */
    private final HttpStatus status;

    /** The message. */
    private final String message;

    /**
     * Instantiates a new mail exception.
     *
     * @param message
     * the message
     */
    // public MailException(String message) {
    // super(message);
    // }

    /**
     * Instantiates a new mail exception.
     *
     * @param message the message
     * @param status the status
     */
    public MailException(String message, HttpStatus status) {

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

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    // public void setStatus(HttpStatus status) {
    // this.status = status;
    // }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    // public void setMessage(String message) {
    // this.message = message;
    // }

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
        return "CityException [status=" + status + ", message=" + message + "]";
    }

}
