package com.mymovieportal.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mymovieportal.dao.SeatBookingDAO;
import com.mymovieportal.dto.SeatBookingDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.SeatBookingException;
import com.mymovieportal.exception.UserException;
import com.mymovieportal.model.SeatBooking;
import com.mymovieportal.repository.MovieRepository;
import com.mymovieportal.repository.SeatBookingRepository;
import com.mymovieportal.repository.TheatreRepository;
import com.mymovieportal.service.CityService;
import com.mymovieportal.service.SeatBookingService;

// TODO: Auto-generated Javadoc
/**
 * The Class SeatBookingServiceImpl.
 */
@Service
public class SeatBookingServiceImpl implements SeatBookingService {

    /** The seat booking dao. */
    @Autowired
    SeatBookingDAO seatBookingDao;

    /** The seat booking repository. */
    @Autowired
    SeatBookingRepository seatBookingRepository;

    /** The city service. */
    @Autowired
    CityService cityService;

    /** The movie repository. */
    @Autowired
    MovieRepository movieRepository;

    /** The theatre repository. */
    @Autowired
    TheatreRepository theatreRepository;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.service.SeatBookingService#getSeatName(com.mymovieportal.
     * model.SeatBooking)
     */

    @Override
    @Transactional
    public List<String> getSeatName(SeatBooking seatbooking) throws SeatBookingException {
        // TODO Auto-generated method stub
        List<String> seatsName = seatBookingDao.getSeatName(seatbooking);

        if (seatsName.size() == 0) {
            throw new SeatBookingException(ExceptionConstants.SEATBOOKINGOPERATIONFAILS, HttpStatus.NOT_FOUND);
        }
        return seatsName;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.service.SeatBookingService#setSeatName(com.mymovieportal.
     * model.SeatBooking)
     */
    @Override
    @Transactional
    public boolean setSeatName(SeatBooking[] seatbooking) throws SeatBookingException {
        // TODO Auto-generated method stub
        boolean result;
        try {
            result = seatBookingDao.setSeatName(seatbooking);
        } catch (DataIntegrityViolationException ex) {
            throw new SeatBookingException(ExceptionConstants.ALREADYSEATBOOKED, HttpStatus.CONFLICT);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.repository.SeatBookingRepository#findBySbUserId(long)
     */

    /**
     * Find by sb user id.
     *
     * @param sbUserId the sb user id
     * @return the seat booking DT o[]
     * @throws CityException the city exception
     * @throws UserException the user exception
     */
    @Override
    @Transactional
    public SeatBookingDTO[] findBySbUserId(long sbUserId) throws CityException, UserException {

        List<Long> userIds = seatBookingRepository.getAllUserIds();
        if (!userIds.contains(sbUserId)) {
            throw new UserException(ExceptionConstants.USERID, HttpStatus.NOT_FOUND);
        }

        SeatBooking[] seatBooking = seatBookingRepository.findBySbUserId(sbUserId);
        SeatBookingDTO[] seatBookingDTO = new SeatBookingDTO[seatBooking.length];
        try {

            for (int i = 0; i < seatBookingDTO.length; i++) {
                seatBookingDTO[i] = new SeatBookingDTO();
                seatBookingDTO[i] = seatBookingDTO[i].getSeatBookingDTO(seatBooking[i]);
                seatBookingDTO[i].setSbCityName(cityService.getCityNameOnly(seatBookingDTO[i].getSbCityId()));
                seatBookingDTO[i].setSbMovieName(movieRepository.getMovieNameOnly(seatBookingDTO[i].getSbMovieId()));
                seatBookingDTO[i]
                    .setSbTheatreName(theatreRepository.getTheatreNameOnly(seatBookingDTO[i].getSbTheatreId()));
            }

            return seatBookingDTO;
        } catch (CityException ex) {
            throw new CityException(ExceptionConstants.CITYOPERATIONFAILS, HttpStatus.NOT_FOUND);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.SeatBookingService#cancelTicket(long)
     */
    @Override
    @Transactional
    public SeatBookingDTO[] cancelTicket(long sbUserId) throws CityException, UserException {

        List<Long> userIds = seatBookingRepository.getAllUserIds();
        if (!userIds.contains(sbUserId)) {
            throw new UserException(ExceptionConstants.USERID, HttpStatus.NOT_FOUND);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        String time = dateFormat1.format(date);

        SeatBooking[] seatBooking = seatBookingRepository.cancelTicket(sbUserId, date1, time);
        SeatBookingDTO[] seatBookingDTO = new SeatBookingDTO[seatBooking.length];
        try {
            for (int i = 0; i < seatBookingDTO.length; i++) {
                seatBookingDTO[i] = new SeatBookingDTO();
                seatBookingDTO[i] = seatBookingDTO[i].getSeatBookingDTO(seatBooking[i]);
                seatBookingDTO[i].setSbCityName(cityService.getCityNameOnly(seatBookingDTO[i].getSbCityId()));
                seatBookingDTO[i].setSbMovieName(movieRepository.getMovieNameOnly(seatBookingDTO[i].getSbMovieId()));
                seatBookingDTO[i]
                    .setSbTheatreName(theatreRepository.getTheatreNameOnly(seatBookingDTO[i].getSbTheatreId()));
            }

            return seatBookingDTO;
        } catch (CityException ex) {
            throw new CityException(ExceptionConstants.CITYOPERATIONFAILS, HttpStatus.NOT_FOUND);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.SeatBookingService#goToCancelTicket(long)
     */
    @Override
    @Transactional
    public int goToCancelTicket(long seatBookingId) throws SeatBookingException {

        List<Long> seatBookingIds = seatBookingRepository.getAllSeatBookingIds();
        if (!seatBookingIds.contains(seatBookingId)) {
            throw new SeatBookingException(ExceptionConstants.SEATBOOKINGOPERATIONFAILS, HttpStatus.NOT_FOUND);
        }

        return seatBookingRepository.goToCancelTicket(seatBookingId);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.SeatBookingService#getSeatNames(long,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<String> getSeatNames(long sbUserId, String sbDate, String sbShowTime, long sbTheatreId,
        long sbMovieid) {
        // TODO Auto-generated method stub
        return seatBookingRepository.getSeatNames(sbUserId, sbDate, sbShowTime, sbTheatreId, sbMovieid);
    }

}
