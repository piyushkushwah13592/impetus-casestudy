package com.mymovieportal.mymovieportal.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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

import com.mymovieportal.dto.TheatreDTO;
import com.mymovieportal.exception.CityException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.CityTheatre;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.repository.CityRepository;
import com.mymovieportal.repository.CityTheatreRepository;
import com.mymovieportal.repository.TheatreRepository;
import com.mymovieportal.service.TheatreService;
import com.mymovieportal.service.impl.TheatreServiceImpl;

public class TheatreServiceImplUnitTest {

    @InjectMocks
    TheatreService theatreService = new TheatreServiceImpl();

    @Mock
    TheatreRepository theatreRepository;

    @Mock
    CityRepository cityRepository;

    @Mock
    CityTheatreRepository cityTheatreRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTheatre() throws TheatreException {
        Theatre theatre = new Theatre();
        theatre.setTheatreId(1);
        theatre.setTheatreName("Inox");
        when(theatreRepository.findByTheatreId(anyLong())).thenReturn(theatre);
        Theatre theatre2 = theatreService.getTheatre(1);
        assertSame(theatre.getTheatreId(), theatre2.getTheatreId());
    }

    @Test(expected = TheatreException.class)
    public void testGetTheatreInvalid() throws TheatreException {
        when(theatreRepository.findByTheatreId(anyLong())).thenReturn(null);
        Theatre theatre2 = theatreService.getTheatre(999);
        assertNull(theatre2);
    }

    @Test
    public void testGetTheatres() {
        List<Theatre> theatreList = new ArrayList<>();
        theatreList.add(new Theatre());
        theatreList.add(new Theatre());
        when(theatreRepository.findAll()).thenReturn(theatreList);
        List<Theatre> theatres = theatreService.getTheatres();
        assertNotNull(theatres);
    }

    @Test
    public void testTheatresByCity() throws CityException {
        List<Long> cityIds = new ArrayList<>();
        cityIds.add(1l);
        cityIds.add(2l);
        when(cityRepository.getCityIds()).thenReturn(cityIds);
        List<Theatre> theatreList = new ArrayList<>();
        theatreList.add(new Theatre());
        theatreList.add(new Theatre());
        when(theatreRepository.getTheatresByCity(anyLong())).thenReturn(theatreList);
        List<Theatre> theatres = theatreService.getTheatresByCity(1);
        assertNotNull(theatres);
    }

    @Test(expected = CityException.class)
    public void testTheatresByCityInvalid() throws CityException {
        List<Long> cityIds = new ArrayList<>();
        cityIds.add(1l);
        cityIds.add(2l);
        when(cityRepository.getCityIds()).thenReturn(cityIds);
        List<Theatre> theatreList = new ArrayList<>();
        when(theatreRepository.getTheatresByCity(anyLong())).thenReturn(theatreList);
        List<Theatre> theatres = theatreService.getTheatresByCity(999);
        assertTrue(theatres.size() == 0);
    }

    @Test
    public void testGetTheatreNameOnly() {
        String theatreName = "INOX";
        when(theatreRepository.getTheatreNameOnly(anyLong())).thenReturn(theatreName);
        String theatreN = theatreService.getTheareNameOnly(1);
        assertSame(theatreName, theatreN);
    }

    @Test
    public void testGetTheatreNameOnlyInvalid() {
        when(theatreRepository.getTheatreNameOnly(anyLong())).thenReturn(null);
        String theatreN = theatreService.getTheareNameOnly(1);
        assertNull(theatreN);
    }

    @Test
    public void testInsertTheatreForExistingMovie() {
        Theatre theatre = new Theatre();
        when(theatreRepository.findByTheatreName(anyString())).thenReturn(theatre);
        Theatre theatre1 = theatreService.insertTheatre(new TheatreDTO());
        assertEquals(theatre.getTheatreStatus(), "active");
        assertNotNull(theatre1);
    }

    @Test
    public void testInsertTheatreForNonExistingMovie() {
        when(theatreRepository.findByTheatreName(anyString())).thenReturn(null);
        List<Long> theatreIds = new ArrayList<>();
        theatreIds.add(1l);
        theatreIds.add(2l);
        when(theatreRepository.getTheatreIds()).thenReturn(theatreIds);
        Theatre theatre = new Theatre();
        theatre.setTheatreStatus("active");
        when(theatreRepository.save(any(Theatre.class))).thenReturn(theatre);
        Theatre theatre1 = theatreService.insertTheatre(new TheatreDTO());
        assertEquals(theatre1.getTheatreStatus(), "active");
    }

    @Test
    public void testDeleteTheatreForExistingTheatre() throws TheatreException {
        when(theatreRepository.findByTheatreNameAndTheatreStatus(anyString(), anyString())).thenReturn(new Theatre());
        Theatre theatre = new Theatre();
        theatre.setTheatreStatus("inactive");
        when(theatreRepository.save(any(Theatre.class))).thenReturn(theatre);
        Theatre theatre1 = theatreService.deleteTheatre(new TheatreDTO());
        assertEquals(theatre1.getTheatreStatus(), "inactive");
    }

    @Test(expected = TheatreException.class)
    public void testDeleteTheatreForNonExistingTheatre() throws TheatreException {
        when(theatreRepository.findByTheatreNameAndTheatreStatus(anyString(), anyString())).thenReturn(null);
        Theatre theatre = theatreService.deleteTheatre(new TheatreDTO());
        assertNull(theatre);
    }

    @Test
    public void testTheatreForDeletion() {
        List<Theatre> theatreList = new ArrayList<>();
        Theatre theatre1 = new Theatre();
        theatre1.setTheatreId(1l);

        Theatre theatre2 = new Theatre();
        theatre2.setTheatreId(2l);

        theatreList.add(theatre1);
        theatreList.add(theatre1);

        when(theatreRepository.getTheatres()).thenReturn(theatreList);

        List<CityTheatre> cityTheatreList = new ArrayList<>();

        CityTheatre cityTheatre1 = new CityTheatre();
        cityTheatre1.setCtTheatreId(1l);

        CityTheatre cityTheatre2 = new CityTheatre();
        cityTheatre2.setCtTheatreId(2l);

        cityTheatreList.add(cityTheatre1);
        cityTheatreList.add(cityTheatre2);

        when(cityTheatreRepository.findAll()).thenReturn(cityTheatreList);

        List<Theatre> theatres = theatreService.getTheatresForDeletion();
        assertNotNull(theatres);

    }

}
