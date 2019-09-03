package com.mymovieportal.mymovieportal.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mymovieportal.dao.CityDAO;
import com.mymovieportal.dao.impl.CityDAOImpl;
import com.mymovieportal.model.City;

public class CityDAOImplTest {

    @InjectMocks
    private CityDAO cityDAO = new CityDAOImpl();

    @Mock
    private SessionFactory sessionFactory;

    private Session session;

    private Query query;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        session = mock(Session.class);

        when(sessionFactory.getCurrentSession()).thenReturn(session);

    }

    @Test
    public void testGetCity() {

        City city = new City();

        when(session.get(City.class, 1L)).thenReturn(city);

        City city1 = cityDAO.getCity(1L);
        assertNotNull(city1);
    }

    @Test
    public void testGetCityInvalid() {

        City city = new City();

        when(session.get(City.class, 999)).thenReturn(null);

        City city1 = cityDAO.getCity(1);
        assertEquals(city1, null);
    }

    @Test
    public void testGetCities() {

        List<City> cityList = new ArrayList<>();
        City city = new City();
        city.setCityId(1);
        city.setCityName("Inodre");

        cityList.add(city);

        query = mock(Query.class);
        when(query.list()).thenReturn(cityList);
        when(session.createQuery(anyString())).thenReturn(query);

        List<City> cityList2 = cityDAO.getCities();
        assertTrue(cityList2.size() == 1);

    }

    @Test
    public void testGetCityNameOnly() {
        City city = new City();
        city.setCityId(1);
        city.setCityName("Inodre");

        when(session.get(City.class, 1L)).thenReturn(city);

        String cityName = cityDAO.getCityNameOnly(1L);
        assertEquals(cityName, city.getCityName());
    }

    @Test
    public void testGetCityNameOnlyInvalid() {
        City city = new City();

        when(session.get(City.class, 999)).thenReturn(null);

        String cityName = cityDAO.getCityNameOnly(999);
        assertEquals(cityName, null);
    }
}
