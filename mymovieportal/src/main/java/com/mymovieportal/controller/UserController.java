package com.mymovieportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mymovieportal.dao.UserDAO;
import com.mymovieportal.dto.LoginDTO;
import com.mymovieportal.dto.ResultDTO;
import com.mymovieportal.dto.UserDTO;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.User;
import com.mymovieportal.service.UserService;
import com.mymovieportal.util.Encryption;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/mymovieportal/user")
public class UserController {

    /** The user service. */
    @Autowired
    UserService userService;

    /** The user dao. */
    @Autowired
    UserDAO userDao;

    /** The logger. */
    Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Save.
     *
     * @param userDTO the user DTO
     * @return the response entity
     */

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {

        if (userDTO.getContactNumber() == null || userDTO.getEmail() == null
            || userDTO.getLastName() == null || userDTO.getName() == null || userDTO.getPassword() == null
            || "".equals(userDTO.getContactNumber().trim()) || "".equals(userDTO.getEmail().trim())
            || "".equals(userDTO.getLastName().trim()) || "".equals(userDTO.getName().trim())
            || "".equals(userDTO.getPassword().trim())) {

            return new ResponseEntity<>(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        boolean result = userService.registerUser(user);

        return ResponseEntity.ok().body(new Gson().toJson(result));

    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the response entity
     */

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("id") long id) {

        if (id < 0) {
            return new ResponseEntity<>(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userService.getUser(id);
            user.setPassword(Encryption.decrypt(user.getPassword()));
            return ResponseEntity.ok().body(new Gson().toJson(user));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Gets the for logging.
     *
     * @param userDTO the user DTO
     * @return the for logging
     */

    @RequestMapping(value = "/loginChecking", method = RequestMethod.POST)
    public ResponseEntity<String> loginChecking(@RequestBody UserDTO userDTO) {

        if (userDTO.getEmail() == null || userDTO.getPassword() == null || "".equals(userDTO.getEmail().trim())
            || "".equals(userDTO.getPassword().trim())) {
            return new ResponseEntity<>(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        try {
            LoginDTO loginDTO = userService.loginChecking(email, password);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            return new ResponseEntity<>(gson.toJson(loginDTO), HttpStatus.CREATED);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());

        }
    }

    /**
     * Update.
     *
     * @param id the id
     * @param userDTO the user DTO
     * @param request the request
     * @return the response entity
     */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody UserDTO userDTO,
        final HttpServletRequest request) {
        boolean result;

        if (id < 0 || userDTO.getPassword() == null || userDTO.getName() == null
            || userDTO.getLastName() == null || userDTO.getEmail() == null || userDTO.getContactNumber() == null
            || "".equals(userDTO.getPassword().trim()) || "".equals(userDTO.getName().trim())
            || "".equals(userDTO.getLastName().trim()) || "".equals(userDTO.getEmail().trim())
            || "".equals(userDTO.getContactNumber().trim())) {

            return new ResponseEntity<>(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        try {
            User user = modelMapper.map(userDTO, User.class);
            result = userService.updateUser(id, user);
            return ResponseEntity.ok().body(gson.toJson(result));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        boolean result;

        if (id < 0) {
            return new ResponseEntity<>(ExceptionConstants.USEROPERATIONFAILS, HttpStatus.BAD_REQUEST);
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        try {
            result = userService.deleteUser(id);
            return ResponseEntity.ok().body(gson.toJson(result));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Gets the contact number.
     *
     * @param contactNumber
     * the contact number
     * @return the contact number
     * @throws UserException
     * SomeException handled
     */

    @RequestMapping(value = "/getContactExistence/{contactNumber}", method = RequestMethod.GET)
    public ResponseEntity<String> getContactNumber(@PathVariable("contactNumber") String contactNumber)
        throws UserException {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        boolean result = userService.getContactNumber(contactNumber);
        return ResponseEntity.ok().body(gson.toJson(result));

    }

    /**
     * Gets the email existence.
     *
     * @param email the email
     * @return the email existence
     */

    @RequestMapping(value = "/getEmailExistence/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<String> getEmailExistence(@PathVariable("email") String email) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        boolean result = userService.getEmailExistence(email);
        return ResponseEntity.ok().body(gson.toJson(result));

    }

    /**
     * Gets the password.
     *
     * @param email the email
     * @return the password
     */
    @RequestMapping(value = "/getPassword/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<String> getPassword(@PathVariable("email") String email) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        try {
            String result = userService.getPassword(email);
            ResultDTO rs = new ResultDTO();
            rs.setResult(result);
            return ResponseEntity.ok().body(gson.toJson(rs));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }

    }

}