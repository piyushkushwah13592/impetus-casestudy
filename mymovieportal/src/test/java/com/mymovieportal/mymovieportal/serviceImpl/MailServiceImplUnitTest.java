package com.mymovieportal.mymovieportal.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.mymovieportal.dto.ConfirmSeatMailDTO;
import com.mymovieportal.dto.UserDTO;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.model.User;
import com.mymovieportal.service.MailService;
import com.mymovieportal.service.MovieService;
import com.mymovieportal.service.TheatreService;
import com.mymovieportal.service.UserService;
import com.mymovieportal.service.impl.MailServiceImpl;

public class MailServiceImplUnitTest {

    @InjectMocks
    private MailService mailService = new MailServiceImpl();

    @Mock
    TheatreService theatreService;

    @Mock
    MovieService movieService;

    @Mock
    private JavaMailSender sender;

    @Mock
    private UserService userService;

    private MimeMessage message;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        message = mock(MimeMessage.class);

    }

    @Test
    public void testForgetPasswordMail() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("piyushkushwah13592@gmail.com");
        userDTO.setPassword("1wwWWW");

        when(sender.createMimeMessage()).thenReturn(message);

        Mockito.doNothing().when(sender).send(any(MimeMessage.class));

        mailService.forgotPasswordMail(userDTO);

    }

    @Test(expected = MessagingException.class)
    public void testForgetPasswordMailInvalid() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("piyushkushwah13592@gmail.com");
        userDTO.setPassword("1wwWWW");
        when(sender.createMimeMessage()).thenReturn(message);
        Mockito.doThrow(MessagingException.class).when(sender).send(any(MimeMessage.class));
        mailService.forgotPasswordMail(userDTO);

    }

    @Test
    public void testConfirmSeatMail() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("piyush@gmail.com");
        when(userService.getUser(anyLong())).thenReturn(user);
        Theatre theatre = new Theatre();
        theatre.setTheatreId(1);
        theatre.setTheatreName("Inox");
        when(theatreService.getTheatre(anyLong())).thenReturn(theatre);
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Raaz");
        when(movieService.getMovie(anyLong())).thenReturn(new Movie());
        when(sender.createMimeMessage()).thenReturn(message);
        Mockito.doNothing().when(sender).send(any(MimeMessage.class));
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreId(1);
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieId(1);
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.confirmSeatMail(confirmSeatMailDTO);
        assertEquals(str, "email sent");
    }

    @Test(expected = UserException.class)
    public void testConfirmSeatMailInvalid() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(null);
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreId(1);
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieId(1);
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.confirmSeatMail(confirmSeatMailDTO);
        assertNull(str);
    }

    @Test(expected = TheatreException.class)
    public void testConfirmSeatMailInvalidTheatreId() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(new User());
        when(theatreService.getTheatre(anyLong())).thenReturn(null);
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreId(1);
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieId(1);
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.confirmSeatMail(confirmSeatMailDTO);
        assertNull(str);
    }

    @Test(expected = MovieException.class)
    public void testConfirmSeatMailInvalidMovieId() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(new User());
        when(theatreService.getTheatre(anyLong())).thenReturn(new Theatre());
        when(movieService.getMovie(anyLong())).thenReturn(null);
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreId(1);
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieId(999);
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.confirmSeatMail(confirmSeatMailDTO);
        assertNull(str);
    }

    @Test
    public void testCancelSeatMail() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("piyush@gmail.com");
        when(userService.getUser(anyLong())).thenReturn(user);
        when(sender.createMimeMessage()).thenReturn(message);
        Mockito.doNothing().when(sender).send(any(MimeMessage.class));
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreName("Inox");
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieName("Raaz");
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.cancelSeatMail(confirmSeatMailDTO);
        assertTrue(str.equals("email sent"));
    }

    @Test(expected = UserException.class)
    public void testCancelSeatMailInvalid() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(null);
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreName("Inox");
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieName("Raaz");
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.cancelSeatMail(confirmSeatMailDTO);
        assertNull(str);
    }

    @Test
    public void testAlertSeatMail() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("piyush@gmail.com");
        when(userService.getUser(anyLong())).thenReturn(user);
        Theatre theatre = new Theatre();
        theatre.setTheatreId(1);
        theatre.setTheatreName("Inox");
        when(theatreService.getTheatre(anyLong())).thenReturn(theatre);
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Raaz");
        when(movieService.getMovie(anyLong())).thenReturn(new Movie());
        when(sender.createMimeMessage()).thenReturn(message);
        Mockito.doNothing().when(sender).send(any(MimeMessage.class));
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreId(1);
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieId(1);
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);

        String str = mailService.showTimeAlertMail(confirmSeatMailDTO);
        assertEquals(str, "email sent");
    }

    @Test(expected = UserException.class)
    public void testAlertSeatMailInvalid() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(null);
        when(theatreService.getTheatre(anyLong())).thenReturn(new Theatre());
        when(movieService.getMovie(anyLong())).thenReturn(new Movie());
        ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
        confirmSeatMailDTO.setDate("2018-04-24");
        confirmSeatMailDTO.setTime("17:00:00");
        confirmSeatMailDTO.setTheatreId(1);
        confirmSeatMailDTO.setUserId(1L);
        confirmSeatMailDTO.setMovieId(1);
        List<String> seatList = new ArrayList<>();
        seatList.add("A1");
        seatList.add("B1");
        confirmSeatMailDTO.setSeatNames(seatList);
        String str = mailService.showTimeAlertMail(confirmSeatMailDTO);
        assertNull(str);
    }
}
