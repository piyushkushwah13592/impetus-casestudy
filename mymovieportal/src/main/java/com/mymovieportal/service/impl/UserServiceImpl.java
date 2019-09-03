package com.mymovieportal.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mymovieportal.dao.UserDAO;
import com.mymovieportal.dto.LoginDTO;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.User;
import com.mymovieportal.repository.SeatBookingRepository;
import com.mymovieportal.service.UserService;
import com.mymovieportal.util.Encryption;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    /** The user dao. */
    @Autowired
    UserDAO userDao;

    /** The seat booking repository. */
    @Autowired
    SeatBookingRepository seatBookingRepository;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.service.UserService#registerUser(com.mymovieportal.model.
     * User)
     */
    @Override
    @Transactional
    public boolean registerUser(User user) {
        // TODO Auto-generated method stub
        String encryptedPassword = Encryption.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        return userDao.registerUser(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#getUsers()
     */
    @Override
    @Transactional
    public List<User> getUsers() {
        // TODO Auto-generated method stub

        return userDao.getUsers();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#getUser(long)
     */
    @Override
    @Transactional
    public User getUser(long id) throws UserException {
        // TODO Auto-generated method stub

        List<Long> userIds = seatBookingRepository.getAllUserIds();
        if (!userIds.contains(id)) {
            throw new UserException(ExceptionConstants.USERID, HttpStatus.NOT_FOUND);
        }

        User user = userDao.getUser(id);

        return user;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#updateUser(long,
     * com.mymovieportal.model.User)
     */
    @Override
    @Transactional
    public boolean updateUser(long id, User user) throws UserException {
        // TODO Auto-generated method stub

        String encryptedPassword = Encryption.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        boolean result = userDao.updateUser(id, user);
        if (!result) {
            throw new UserException(ExceptionConstants.USERID, HttpStatus.NOT_FOUND);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#deleteUser(long)
     */
    @Override
    @Transactional
    public boolean deleteUser(long id) throws UserException {
        // TODO Auto-generated method stub

        boolean result = userDao.deleteUser(id);
        if (!result) {
            throw new UserException(ExceptionConstants.USERID, HttpStatus.NOT_FOUND);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#getContactNumber(java.lang.String)
     */
    @Override
    @Transactional
    public boolean getContactNumber(String contactNumber) {
        // TODO Auto-generated method stub

        boolean result = userDao.getContactNumber(contactNumber);

        return result;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.service.UserService#getEmailExistence(java.lang.String)
     */
    @Override
    @Transactional
    public boolean getEmailExistence(String email) {
        // TODO Auto-generated method stub

        boolean result = userDao.getEmailExistence(email);
        // if (!result)
        // throw new UserException(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.NOT_FOUND);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#getPassword(java.lang.String)
     */
    @Override
    @Transactional
    public String getPassword(String email) throws UserException {
        // TODO Auto-generated method stub

        String pass = userDao.getPassword(email);
        if (pass == null) {
            throw new UserException(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.NOT_FOUND);
        }
        return pass;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.UserService#loginChecking(java.lang.String,
     * java.lang.String)
     */
    @Override
    @Transactional
    public LoginDTO loginChecking(String email, String password) throws UserException {
        // TODO Auto-generated method stub

        String encryptedPassword = Encryption.encrypt(password);
        LoginDTO loginDTO = userDao.loginChecking(email, encryptedPassword);
        if (loginDTO == null) {
            throw new UserException(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.NOT_FOUND);
        }
        return loginDTO;
    }

}
