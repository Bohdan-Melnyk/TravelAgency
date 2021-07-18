package com.softserve.travelagency.security;

import com.softserve.travelagency.dao.UserDAO;
import com.softserve.travelagency.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailsServiseImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDAO.getUserByEmail(email);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return SecurityUser.fromUser(user.get());
    }
}
