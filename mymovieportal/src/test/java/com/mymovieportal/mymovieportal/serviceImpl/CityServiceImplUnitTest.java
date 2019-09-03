package com.mymovieportal.mymovieportal.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.mymovieportal.dao.CityDAO;
import com.mymovieportal.dto.CityDTO;
import com.mymovieportal.dto.CityTheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.model.City;
import com.mymovieportal.model.CityTheatre;
import com.mymovieportal.repository.CityRepository;
import com.mymovieportal.repository.CityTheatreRepository;
import com.mymovieportal.service.CityService;
import com.mymovieportal.service.impl.CityServiceImpl;

public class CityServiceImplUnitTest {

    @InjectMocks
    private CityService cityService = new CityServiceImpl();

    @Mock
    private CityDAO cityDao;

    @Mock
    private CityRepository cityRepository;

    @Mock
    CityTheatreRepository cityTheatreRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCity() throws CityException {
        City city = new City();
        city.setCityId(1);
        city.setCityName("Indore");
        when(cityDao.getCity(anyLong())).thenReturn(city);
        City city2 = cityService.getCity(city.getCityId());
        assertEquals(city2.getCityName(), city.getCityName());
    }

    @Test(expected = CityException.class)
    public void testGetCityInvalid() throws CityException {
        when(cityDao.getCity(999L)).thenReturn(null);
        City city2 = cityService.getCity(999L);
        assertNull(city2);
    }

    @Test
    public void testGetCities() {
        List<City> cities = new ArrayList<>();
        when(cityDao.getCities()).thenReturn(cities);
        List<City> cityList = cityService.getCities();
        assertNotNull(cityList);
    }

    @Test
    public void testGetCityNameOnly() throws CityException {
        String cityName = "Indore";
        when(cityDao.getCityNameOnly(anyLong())).thenReturn(cityName);
        String cityN = cityService.getCityNameOnly(1);
        assertSame(cityN, cityName);
    }

    @Test(expected = CityException.class)
    public void testGetCityNameOnlyInvalid() throws CityException {
        when(cityDao.getCityNameOnly(anyLong())).thenReturn(null);
        String cityN = cityService.getCityNameOnly(999);
        assertNull(cityN);
    }

    @Test
    public void testInsertCityForExistingCity() throws CityException {
        City city = new City();
        when(cityRepository.findByCityName(anyString())).thenReturn(city);
        City city1 = cityService.insertCity(new CityDTO());
        assertEquals(city.getCityStatus(), "active");
        assertNotNull(city1);
    }

    @Test
    public void testInsertCityForNonExistingCity() throws CityException {
        when(cityRepository.findByCityName(anyString())).thenReturn(null);
        List<Long> cityIds = new ArrayList<>();
        cityIds.add(1l);
        cityIds.add(2l);
        when(cityRepository.getCityIds()).thenReturn(cityIds);
        City city = new City();
        city.setCityStatus("active");
        when(cityRepository.save(any(City.class))).thenReturn(city);
        City city1 = cityService.insertCity(new CityDTO());
        assertEquals(city1.getCityStatus(), "active");
    }

    @Test(expected = CityException.class)
    public void testDeleteCityForNonExistingCity() throws CityException {
        when(cityRepository.findByCityNameAndCityStatus(anyString(), anyString())).thenReturn(null);
        City city1 = cityService.deleteCity(new CityDTO());
        assertNull(city1);
    }

    @Test
    public void testDeleteCityForExistingCity() throws CityException {
        when(cityRepository.findByCityNameAndCityStatus(anyString(), anyString())).thenReturn(new City());
        City city = new City();
        city.setCityStatus("inactive");
        when(cityRepository.save(any(City.class))).thenReturn(city);
        City city1 = cityService.deleteCity(new CityDTO());
        assertEquals(city1.getCityStatus(), "inactive");

    }

    @Test
    public void testMapCityTheatre() throws CityException {

        CityTheatreDTO entity = new CityTheatreDTO();
        entity.setCtCityId(1l);
        entity.setCtTheatreId(2l);

        CityTheatre entity2 = new CityTheatre();
        when(cityTheatreRepository.save(any(CityTheatre.class))).thenReturn(entity2);

        CityTheatreDTO cityTheatreDTO = cityService.mapCityTheatre(entity);
        assertNotNull(cityTheatreDTO);
    }

    @Test(expected = CityException.class)
    public void testMapCityTheatreError() throws CityException {

        CityTheatreDTO entity = new CityTheatreDTO();
        entity.setCtCityId(1l);
        entity.setCtTheatreId(2l);

        when(cityTheatreRepository.save(any(CityTheatre.class))).thenThrow(DataIntegrityViolationException.class);

        CityTheatreDTO cityTheatreDTO = cityService.mapCityTheatre(entity);
    }

    @Test
    public void testGetCitiesForDeletion() throws CityException {

        List<City> cityList = new ArrayList<>();

        City city1 = new City();
        city1.setCityId(1l);

        City city2 = new City();
        city2.setCityId(2l);

        cityList.add(city2);
        cityList.add(city1);

        when(cityDao.getCities()).thenReturn(cityList);

        List<CityTheatre> cityTheatreList = new ArrayList<>();

        CityTheatre cityTheatre1 = new CityTheatre();
        cityTheatre1.setCtCityId(1l);

        CityTheatre cityTheatre2 = new CityTheatre();
        cityTheatre2.setCtCityId(2l);

        cityTheatreList.add(cityTheatre1);
        cityTheatreList.add(cityTheatre2);

        when(cityTheatreRepository.findAll()).thenReturn(cityTheatreList);

        List<City> movieList2 = cityService.getCitiesForDeletion();
        assertNotNull(movieList2);
    }

}
