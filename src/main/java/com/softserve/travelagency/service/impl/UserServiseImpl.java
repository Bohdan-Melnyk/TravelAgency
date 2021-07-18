package com.softserve.travelagency.service.impl;

import com.softserve.travelagency.dao.UserDAO;
import com.softserve.travelagency.model.Role;
import com.softserve.travelagency.model.User;
import com.softserve.travelagency.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiseImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiseImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean create(User user) {
       /* Optional<User> userDB = userDAO.getUserByEmail(user.getEmail());

        if (userDB.isPresent()) {
            return false;
        }*/

        user.setRole(Role.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userDAO.create(user);
        return true;
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
        User user = userDAO.getUserByEmail(email).orElse(null);
        if (user != null) {
            return user;
        } else return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
