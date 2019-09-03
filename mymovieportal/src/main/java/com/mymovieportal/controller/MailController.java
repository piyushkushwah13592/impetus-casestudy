package com.mymovieportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mymovieportal.dto.ConfirmSeatMailDTO;
import com.mymovieportal.dto.ResultDTO;
import com.mymovieportal.dto.UserDTO;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.MailException;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.service.MailService;

// TODO: Auto-generated Javadoc
/**
 * The Class MailController.
 */
@RestController
@RequestMapping("/mymovieportal/mail")
public class MailController {

    /** The mail service. */
    @Autowired
    MailService mailService;

    /**
     * Mail.
     *
     * @param userDTO the user DTO
     * @return the string
     * @throws Exception the exception SomeException handled
     */
    @RequestMapping(value = "/forgotPasswordMail", method = RequestMethod.POST)
    public ResponseEntity<String> forgotPasswordMail(@RequestBody UserDTO userDTO) throws Exception {

        if (userDTO.getEmail() == null || "".equals(userDTO.getEmail().trim())
            || userDTO.getPassword() == null || "".equals(userDTO.getPassword().trim())) {

            return new ResponseEntity<>(ExceptionConstants.MAILINPUTS, HttpStatus.BAD_REQUEST);
        }

        String result = mailService.forgotPasswordMail(userDTO);
        ResultDTO rs = new ResultDTO();
        rs.setResult(result);
        return ResponseEntity.ok().body(new Gson().toJson(rs));
    }

    /**
     * Confim seat mail.
     *
     * @param confirmSeatMailDTO the confirm seat mail DTO
     * @return the response entity
     * @throws Exception the exception SomeException handled
     */
    @RequestMapping(value = "/confirmSeatMail", method = RequestMethod.POST)
    public ResponseEntity<String> confimSeatMail(@RequestBody ConfirmSeatMailDTO confirmSeatMailDTO)
        throws Exception {

        if (confirmSeatMailDTO.getId() < 0 || confirmSeatMailDTO.getDate() == null
        // || confirmSeatMailDTO.getMovieId() == null
            || confirmSeatMailDTO.getSeatNames().size() < 1
            // || confirmSeatMailDTO.getTheatreId() == null
            || confirmSeatMailDTO.getTime() == null
            || "".equals(confirmSeatMailDTO.getTime().trim())
            || "".equals(confirmSeatMailDTO.getDate().trim())
        // || "".equals(confirmSeatMailDTO.getMovieId().trim())
        // || "".equals(confirmSeatMailDTO.getTheatreId().trim())
        ) {

            return new ResponseEntity<>(ExceptionConstants.MAILINPUTS, HttpStatus.BAD_REQUEST);
        }
        try {
            String result = mailService.confirmSeatMail(confirmSeatMailDTO);
            ResultDTO rs = new ResultDTO();
            rs.setResult(result);
            return ResponseEntity.ok().body(new Gson().toJson(rs));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        } catch (MovieException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        } catch (TheatreException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }

    /**
     * Cancel seat mail.
     *
     * @param confirmSeatMailDTO the confirm seat mail DTO
     * @return the response entity
     * @throws Exception the exception SomeException handled
     */
    @RequestMapping(value = "/cancelSeatMail", method = RequestMethod.POST)
    public ResponseEntity<String> cancelSeatMail(@RequestBody ConfirmSeatMailDTO confirmSeatMailDTO)
        throws Exception {

        if (confirmSeatMailDTO == null || confirmSeatMailDTO.getId() < 0 || confirmSeatMailDTO.getDate() == null
        // || confirmSeatMailDTO.getMovieId() == null
            || confirmSeatMailDTO.getSeatNames().size() < 1
            // || confirmSeatMailDTO.getTheatreId() == null
            || confirmSeatMailDTO.getTime() == null
            || "".equals(confirmSeatMailDTO.getTime().trim())
            || "".equals(confirmSeatMailDTO.getDate().trim())
        // || "".equals(confirmSeatMailDTO.getMovieId().trim())
        // || "".equals(confirmSeatMailDTO.getTheatreId().trim())
        ) {
            throw new MailException(ExceptionConstants.MAILINPUTS, HttpStatus.BAD_REQUEST);
        }

        try {
            String result = mailService.cancelSeatMail(confirmSeatMailDTO);
            ResultDTO rs = new ResultDTO();
            rs.setResult(result);
            return ResponseEntity.ok().body(new Gson().toJson(rs));
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
        }
    }
}
