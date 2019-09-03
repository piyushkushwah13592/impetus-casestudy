package com.mymovieportal.mymovieportal.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mymovieportal.dao.UserDAO;
import com.mymovieportal.dao.impl.UserDAOImpl;
import com.mymovieportal.dto.LoginDTO;
import com.mymovieportal.model.User;

public class UserDAOImplTest {

    @InjectMocks
    private UserDAO userDAO = new UserDAOImpl();

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
    public void testRegisterUser() {

        User user = new User();

        user.setName("Piyush");
        user.setLastName("Kushwah");
        user.setContactNumber("9009897989");
        user.setPassword("password");
        user.setEmail("p@gmail.com");

        boolean flag = userDAO.registerUser(user);
        assertEquals(user.getUserRole(), "user");
        assertTrue(flag);

    }

    @Ignore
    public void testRegisterUserInvalid() {

        User user = new User();
        user.setName("Piyush");
        user.setLastName("Kushwah");
        user.setEmail("p@gmail.com");

        Mockito.doThrow(Exception.class).when(session).persist(any(User.class));

        boolean flag = userDAO.registerUser(user);

        assertFalse(flag);
    }

    @Test
    public void testGetUser() {

        User user = new User();
        when(session.get(User.class, 1l)).thenReturn(user);

        User user1 = userDAO.getUser(1L);
        assertNotNull(user1);
    }

    @Test
    public void testGetUsers() {

        query = mock(Query.class);
        when(session.createQuery(anyString())).thenReturn(query);
        List<User> userList = userDAO.getUsers();
        assertNotNull(userList);
    }

    @Test
    public void testUpdateUser() {

        User user = new User();
        when(session.get(User.class, 1l)).thenReturn(user);

        boolean flag = userDAO.updateUser(1L, new User());
        assertTrue(flag);
    }

    @Test
    public void testUpdateUserInvalid() {

        when(session.get(User.class, 1l)).thenReturn(null);
        Mockito.doThrow(Exception.class).when(session).update(any(User.class));
        boolean flag = userDAO.updateUser(1L, new User());
        assertFalse(flag);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        when(session.get(User.class, 1l)).thenReturn(user);
        boolean flag = userDAO.deleteUser(1l);
        assertTrue(flag);
    }

    @Test
    public void testDeleteUserInvalid() {
        Mockito.doThrow(Exception.class).when(session).delete(any(User.class));
        boolean flag = userDAO.deleteUser(1l);
        assertFalse(flag);
    }

    @Test
    public void testGetContactNumber() {

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);
        boolean flag = userDAO.getContactNumber("9009897989");
        assertTrue(flag);

    }

    @Test
    public void testGetContactNumberInvalid() {

        List<User> userList = new ArrayList<>();

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);
        boolean flag = userDAO.getContactNumber("9009899");
        assertFalse(flag);

    }

    @Test
    public void testGetEmailExistence() {

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);
        boolean flag = userDAO.getEmailExistence("piyushkushwah@gmail.com");
        assertTrue(flag);
    }

    @Test
    public void testGetEmailExistenceInvalid() {

        List<User> userList = new ArrayList<>();

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);
        boolean flag = userDAO.getEmailExistence("piyushkushwah@gmail.com");
        assertFalse(flag);
    }

    @Test
    public void testGetPassword() {

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setPassword("1wwWWW");

        userList.add(user1);
        userList.add(new User());

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);

        String password = userDAO.getPassword("piyushkushwah@gmail.com");
        assertEquals("1wwWWW", password);

    }

    @Test
    public void testGetPasswordInvalid() {

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setPassword("1wwWWW");

        userList.add(user1);
        userList.add(new User());

        Mockito.doThrow(Exception.class).when(session).createQuery(anyString());

        String password = userDAO.getPassword("piyushkushwah@gmail.com");
        assertNull(password);

    }

    @Test
    public void testLoginCheking() {

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setPassword("1wwWWW");
        user1.setEmail("piyushkushwah13592@gmail.com");

        userList.add(user1);
        userList.add(new User());

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);

        LoginDTO loginDTO = userDAO.loginChecking("piyushkushwah13592@gmail.com", "1wwWWW");
        assertNotNull(loginDTO);

    }

    @Test
    public void testLoginChekingForNonUser() {

        List<User> userList = new ArrayList<>();

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);

        LoginDTO loginDTO = userDAO.loginChecking("piyushkushwah@gmail.com", "1wwWWW");
        assertNull(loginDTO);

    }

    @Test
    public void testLoginChekingForInavlidPassword() {

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setPassword("1wwWWW");
        user1.setEmail("piyushkushwah13592@gmail.com");

        userList.add(user1);
        userList.add(new User());

        query = mock(Query.class);
        when(query.list()).thenReturn(userList);
        when(session.createQuery(anyString())).thenReturn(query);

        LoginDTO loginDTO = userDAO.loginChecking("piyushkushwah13592@gmail.com", "1wwWWe");
        assertNull(loginDTO);

    }
}
