package com.mymovieportal.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mymovieportal.dao.UserDAO;
import com.mymovieportal.dto.LoginDTO;
import com.mymovieportal.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAOImpl.
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Gets the session.
     *
     * @return the session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#registerUser(com.mymovieportal.model.User)
     */
    @Override
    public boolean registerUser(User user) {

        // TODO Auto-generated method stub
        user.setUserRole("user");
        getSession().persist(user);
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#getUser(long)
     */
    @Override
    public User getUser(long id) {
        // TODO Auto-generated method stub

        User user = getSession().get(User.class, id);
        return user;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#getUsers()
     */
    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        Query query = getSession().createQuery("from User");
        List<User> userList = query.list();
        return userList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#updateUser(long,
     * com.mymovieportal.model.User)
     */
    @Override
    public boolean updateUser(long id, User user) {
        // TODO Auto-generated method stub

        User user2 = getSession().get(User.class, id);
        if (user2 == null) {
            return false;
        }
        user2.setName(user.getName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());
        user2.setContactNumber(user.getContactNumber());
        user2.setPassword(user.getPassword());
        getSession().update(user2);
        return true;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#deleteUser(long)
     */
    @Override
    public boolean deleteUser(long id) {
        // TODO Auto-generated method stub
        try {
            User user = getSession().get(User.class, id);
            getSession().delete(user);
            return true;
        } catch (Exception ex) {
            // System.out.println("error in UserDAOImpl deleteUser() " + ex);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#getContactNumber(java.lang.String)
     */
    @Override
    public boolean getContactNumber(String contactNumber) {
        // TODO Auto-generated method stub
        String myquery = "from User where contactNumber=:number";
        Query query = getSession().createQuery(myquery);
        query.setParameter("number", contactNumber);
        List<User> userList = query.list();
        if (userList.size() != 0) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#getEmailExistence(java.lang.String)
     */
    @Override

    public boolean getEmailExistence(String email) {
        // TODO Auto-generated method stub

        String myquery = "from User where email=:emailParam";
        Query query = getSession().createQuery(myquery);
        query.setParameter("emailParam", email);
        List<User> userList = query.list();
        if (userList.size() != 0) {
            return true;
        }
        return false;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mymovieportal.dao.UserDAO#getPassword(java.lang.String)
     */
    @Override
    public String getPassword(String email) {
        // TODO Auto-generated method stub
        try {
            String myquery = "from User where email=:emailParam";
            Query query = getSession().createQuery(myquery);
            query.setParameter("emailParam", email);
            List<User> userList = query.list();
            User user = userList.get(0);
            return user.getPassword();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public LoginDTO loginChecking(String email, String password) {
        // TODO Auto-generated method stub
        String myquery = "from User where email=:emailParam";
        Query query = getSession().createQuery(myquery);
        query.setParameter("emailParam", email);
        List<User> userList = query.list();
        if (userList.size() == 0) {
            return null;
        }
        User user = userList.get(0);

        if (user != null && user.getPassword().equals(password)) {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setId(user.getId());
            loginDTO.setRole(user.getUserRole());
            return loginDTO;
        }
        return null;
    }

}
