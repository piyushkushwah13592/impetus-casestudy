package com.mymovieportal.mymovieportal.serviceImpl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.mymovieportal.mymovieportal.controller.AdminControllerTest;
import com.mymovieportal.mymovieportal.controller.CityControllerTest;
import com.mymovieportal.mymovieportal.controller.MovieControllerTest;
import com.mymovieportal.mymovieportal.controller.SeatBookingControllerTest;
import com.mymovieportal.mymovieportal.controller.TheatreControllerTest;
import com.mymovieportal.mymovieportal.daoImpl.CityDAOImplTest;
import com.mymovieportal.mymovieportal.daoImpl.SeatBookingDAOImplTest;
import com.mymovieportal.mymovieportal.daoImpl.UserDAOImplTest;
import com.mymovieportal.mymovieportal.dto.AllDTOUnitTest;
import com.mymovieportal.mymovieportal.model.AllModelUnitTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    CityServiceImplUnitTest.class,
    MovieServiceImplUnitTest.class,
    SeatBookingServiceImplUnitTest.class,
    TheatreServiceImplUnitTest.class,
    UserServiceImplUnitTest.class,
    MailServiceImplUnitTest.class,
    AllDTOUnitTest.class,
    UserDAOImplTest.class,
    SeatBookingDAOImplTest.class,
    CityDAOImplTest.class,
    AllModelUnitTest.class,
    AdminControllerTest.class,
    CityControllerTest.class,
    MovieControllerTest.class,
    SeatBookingControllerTest.class,
    TheatreControllerTest.class
})

public class TestSuite {

}
