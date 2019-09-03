package com.mymovieportal.service;

import com.mymovieportal.dto.ConfirmSeatMailDTO;
import com.mymovieportal.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MailService.
 */
public interface MailService {

    /**
     * Confirm seat mail.
     *
     * @param confirmSeatMailDTO the confirm seat mail DTO
     * @return the string
     * @throws Exception the exception
     */
    String confirmSeatMail(ConfirmSeatMailDTO confirmSeatMailDTO) throws Exception;

    /**
     * Forgot password mail.
     *
     * @param confirmSeatMailDTO the confirm seat mail DTO
     * @return the string
     * @throws Exception the exception
     */


    /**
     * Cancel seat mail.
     *
     * @param confirmSeatMailDTO the confirm seat mail DTO
     * @return the string
     * @throws Exception the exception
     */
    String cancelSeatMail(ConfirmSeatMailDTO confirmSeatMailDTO) throws Exception;

    /**
     * Forgot password mail.
     *
     * @param userDTO the user DTO
     * @return the string
     * @throws Exception the exception
     */
    String forgotPasswordMail(UserDTO userDTO) throws Exception;

    /**
     * Show time alert mail.
     *
     * @param confirmSeatMailDTO the confirm seat mail DTO
     * @return the string
     * @throws Exception the exception
     */
    String showTimeAlertMail(ConfirmSeatMailDTO confirmSeatMailDTO) throws Exception;
}
