package com.softserve.travelagency.service;

import com.softserve.travelagency.model.User;

import java.util.List;

public interface UserService {
<<<<<<< HEAD
    boolean create(User user);
=======
    boolean addUser(User user);
>>>>>>> 4d000236bd66c088a7035a3010876045fee6a984

    User readById(Long id);

    void delete(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
