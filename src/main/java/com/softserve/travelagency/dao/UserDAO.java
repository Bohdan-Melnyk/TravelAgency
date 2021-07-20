package com.softserve.travelagency.dao;

import com.softserve.travelagency.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void create(User user);

    User readById(Long id);

    void delete(Long id);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();
}
