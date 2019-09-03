package com.mymovieportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mymovieportal.dto.TheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.CityTheatre;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.repository.CityRepository;
import com.mymovieportal.repository.CityTheatreRepository;
import com.mymovieportal.repository.TheatreRepository;
import com.mymovieportal.service.TheatreService;

// TODO: Auto-generated Javadoc
/**
 * The Class TheatreServiceImpl.
 */
@Service
public class TheatreServiceImpl implements TheatreService {

    /** The theatre repository. */
    @Autowired
    TheatreRepository theatreRepository;

    /** The city repository. */
    @Autowired
    CityRepository cityRepository;

    /** The city theatre repository. */
    @Autowired
    CityTheatreRepository cityTheatreRepository;

    /**
     * Gets the theatre.
     *
     * @param theatreId the theatre id
     * @return the theatre
     * @throws TheatreException the theatre exception
     */
    @Override
    @Transactional
    public Theatre getTheatre(long theatreId) throws TheatreException {
        // TODO Auto-generated method stub
        Theatre theatre = theatreRepository.findByTheatreId(theatreId);
        if (theatre == null) {
            throw new TheatreException(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.NOT_FOUND);
        }
        return theatre;
    }

    /**
     * Gets the theatres.
     *
     * @return the theatres
     */
    @Override
    @Transactional
    public List<Theatre> getTheatres() {
        // TODO Auto-generated method stub
        return theatreRepository.getTheatres();
    }


    /* (non-Javadoc)
     * @see com.mymovieportal.service.TheatreService#getTheatresForDeletion()
     */
    @Override
    @Transactional
    public List<Theatre> getTheatresForDeletion() {
        // TODO Auto-generated method stub
        List<Theatre> theatreList = theatreRepository.getTheatres();

        List<CityTheatre> cityTheatreList = cityTheatreRepository.findAll();

        List<Theatre> tempList = new ArrayList<>();

        for (int i = 0; i < theatreList.size(); i++) {
            for (int j = 0; j < cityTheatreList.size(); j++) {
                if (theatreList.get(i).getTheatreId() == cityTheatreList.get(j).getCtTheatreId()) {
                    tempList.add(theatreList.get(i));
                }
            }
        }
        theatreList.removeAll(tempList);
        return theatreList;
    }

    /**
     * Gets the theatres by city.
     *
     * @param cityId the city id
     * @return the theatres by city
     * @throws CityException the city exception
     */

    @Override
    @Transactional
    public List<Theatre> getTheatresByCity(long cityId) throws CityException {
        // TODO Auto-generated method stub

        List<Long> cityIds = cityRepository.getCityIds();
        if (!cityIds.contains(cityId)) {
            throw new CityException(ExceptionConstants.CITYID, HttpStatus.NOT_FOUND);
        }
        return theatreRepository.getTheatresByCity(cityId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.service.TheatreService#getTheareNameOnly(java.lang.String)
     */
    @Override
    @Transactional
    public String getTheareNameOnly(long theatreId) {
        return theatreRepository.getTheatreNameOnly(theatreId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mymovieportal.service.TheatreService#insertTheatre(com.mymovieportal.dto.
     * TheatreDTO)
     */
    @Override
    @Transactional
    public Theatre insertTheatre(TheatreDTO theatreDTO) {
        // TODO Auto-generated method stub

        Theatre theatre1 = theatreRepository.findByTheatreName(theatreDTO.getTheatreName());
        if (theatre1 != null) {
            theatre1.setTheatreStatus("active");
            return theatre1;
        }

        /*
         * List<String> theatreIds = theatreRepository.getTheatreIds();
         * String id = theatreIds.get(theatreIds.size() - 1);
         * String ch = "t";
         * id = id.substring(1);
         * int theatreId = Integer.parseInt(id);
         * if (theatreId < 9) {
         * ch += "0";
         * }
         * theatreId++;
         */
        Theatre theatre = new Theatre();
        theatre.setTheatreName(theatreDTO.getTheatreName());
        // theatre.setTheatreId(ch + theatreId);
        theatre.setTheatreStatus("active");
        Theatre response = theatreRepository.save(theatre);
        return response;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.TheatreService#deleteTheatre(com.mymovieportal.dto.TheatreDTO)
     */
    @Override
    @Transactional
    public Theatre deleteTheatre(TheatreDTO theatreDTO) throws TheatreException {
        // TODO Auto-generated method stub

        Theatre theatre = theatreRepository.findByTheatreNameAndTheatreStatus(theatreDTO.getTheatreName(), "active");
        if (theatre == null) {
            throw new TheatreException(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.NOT_FOUND);
        }
        theatre.setTheatreName(theatre.getTheatreName());
        theatre.setTheatreStatus("inactive");
        theatre.setTheatreId(theatre.getTheatreId());
        Theatre response = theatreRepository.save(theatre);
        return response;

    }
}
