package com.softserve.travelagency.service;

import com.softserve.travelagency.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    User readById(Long id);

    void delete(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
