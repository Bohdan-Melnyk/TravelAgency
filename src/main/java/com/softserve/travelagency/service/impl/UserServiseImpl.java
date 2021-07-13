package com.softserve.travelagency.service.impl;

import com.softserve.travelagency.dao.UserDAO;
import com.softserve.travelagency.model.User;
import com.softserve.travelagency.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiseImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiseImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void create(User user) {
        if (user != null) {
            userDAO.create(user);
        } else throw new NullPointerException("User is null");
    }

    @Override
    public User readById(Long id) {
        User user = userDAO.readById(id);
        if (user != null) {
            return user;
        } else throw new NullPointerException("User is null");
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            return user;
        } else return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
