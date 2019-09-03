package com.mymovieportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mymovieportal.dao.CityDAO;
import com.mymovieportal.dto.CityDTO;
import com.mymovieportal.dto.CityTheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.model.City;
import com.mymovieportal.model.CityTheatre;
import com.mymovieportal.repository.CityRepository;
import com.mymovieportal.repository.CityTheatreRepository;
import com.mymovieportal.service.CityService;

// TODO: Auto-generated Javadoc
/**
 * The Class CityServiceImpl.
 */
@Service
public class CityServiceImpl implements CityService {

    /** The city dao. */
    @Autowired
    CityDAO cityDao;

    /** The city repository. */
    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityTheatreRepository cityTheatreRepository;

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.CityService#getCity(java.lang.String)
     */
    @Override
    @Transactional
    public City getCity(long cityId) throws CityException {
        // TODO Auto-generated method stub
        City city = cityDao.getCity(cityId);
        if (city == null) {
            throw new CityException(ExceptionConstants.CITYID, HttpStatus.NOT_FOUND);
        }
        return city;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.CityService#getCities()
     */
    @Override
    @Transactional
    public List<City> getCities() {
        // TODO Auto-generated method stub
        return cityDao.getCities();
    }

    @Override
    @Transactional
    public List<City> getCitiesForDeletion() {
        // TODO Auto-generated method stub
        // return cityDao.getCities();
        List<City> cityList = cityDao.getCities();

        List<CityTheatre> cityTheatreList = cityTheatreRepository.findAll();

        List<City> tempList = new ArrayList<>();

        for (int i = 0; i < cityList.size(); i++) {
            for (int j = 0; j < cityTheatreList.size(); j++) {
                if (cityList.get(i).getCityId() == cityTheatreList.get(j).getCtCityId()) {
                    tempList.add(cityList.get(i));
                }
            }
        }
        cityList.removeAll(tempList);
        return cityList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.CityService#getCityNameOnly(java.lang.String)
     */
    @Override
    @Transactional
    public String getCityNameOnly(long cityId) throws CityException {
        // TODO Auto-generated method stub
        String city = cityDao.getCityNameOnly(cityId);
        if (city == null) {
            throw new CityException(ExceptionConstants.CITYID, HttpStatus.NOT_FOUND);
        }
        return city;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.CityService#insertCity(com.mymovieportal.dto.
     * CityDTO)
     */
    @Override
    @Transactional
    public City insertCity(CityDTO cityDTO) {
        // TODO Auto-generated method stub

        City city1 = cityRepository.findByCityName(cityDTO.getCityName());
        if (city1 != null) {
            city1.setCityStatus("active");
            return city1;
        }

        /*
         * List<String> cityIds = cityRepository.getCityIds(); String id =
         * cityIds.get(cityIds.size() - 1); String ch = "c"; id = id.substring(1); int
         * cityId = Integer.parseInt(id); if (cityId < 9) { ch += "0"; } cityId++;
         */

        City city = new City();
        city.setCityName(cityDTO.getCityName());
        // city.setCityId(ch + cityId);
        city.setCityStatus("active");
        City response = cityRepository.save(city);
        return response;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.service.CityService#deleteCity(com.mymovieportal.dto.
     * CityDTO)
     */
    @Override
    @Transactional
    public City deleteCity(CityDTO cityDTO) throws CityException {
        // TODO Auto-generated method stub

        City city = cityRepository.findByCityNameAndCityStatus(cityDTO.getCityName(), "active");
        if (city == null) {
            throw new CityException(ExceptionConstants.CITYID, HttpStatus.NOT_FOUND);
        }
        city.setCityName(city.getCityName());
        city.setCityStatus("inactive");
        //city.setCityId(city.getCityId());
        City response = cityRepository.save(city);
        return response;

    }

    @Override
    public CityTheatreDTO mapCityTheatre(CityTheatreDTO theatreDTO) throws CityException {
        // CityTheatreDTO cityTheatreDto = null;

        CityTheatre cityTheatre = null;
        CityTheatre entity = new CityTheatre();
        entity.setCtCityId(theatreDTO.getCtCityId());
        entity.setCtTheatreId(theatreDTO.getCtTheatreId());
        try {
            cityTheatre = cityTheatreRepository.save(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new CityException(ExceptionConstants.CITYTHEATREID, HttpStatus.CONFLICT);
        }

        ModelMapper modelMapper = new ModelMapper();
        CityTheatreDTO cityTheatreDto = modelMapper.map(cityTheatre, CityTheatreDTO.class);

        return cityTheatreDto;
    }

}
