package com.mymovieportal.service;

import java.util.List;

import com.mymovieportal.dto.LoginDTO;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

    /**
     * Register user.
     *
     * @param user the user
     * @return true, if successful
     */
    boolean registerUser(User user);

    /**
     * Gets the user.
     *
     * @param id the id
     * @return the user
     * @throws UserException the user exception
     */
    User getUser(long id) throws UserException;

    /**
     * Gets the users.
     *
     * @return the users
     */
    List<User> getUsers();

    /**
     * Login checking.
     *
     * @param email the email
     * @param password the password
     * @return the login DTO
     * @throws UserException the user exception
     */
    LoginDTO loginChecking(String email, String password) throws UserException;

    /**
     * Update user.
     *
     * @param id the id
     * @param user the user
     * @return true, if successful
     * @throws UserException the user exception
     */
    boolean updateUser(long id, User user) throws UserException;

    /**
     * Delete user.
     *
     * @param id the id
     * @return true, if successful
     * @throws UserException the user exception
     */
    boolean deleteUser(long id) throws UserException;

    /**
     * Gets the contact number.
     *
     * @param contactNumber the contact number
     * @return the contact number
     *
     */
    boolean getContactNumber(String contactNumber);

    /**
     * Gets the email existence.
     *
     * @param email the email
     * @return the email existence
     *
     */
    boolean getEmailExistence(String email);

    /**
     * Gets the password.
     *
     * @param email the email
     * @return the password
     * @throws UserException the user exception
     */
    String getPassword(String email) throws UserException;

}
