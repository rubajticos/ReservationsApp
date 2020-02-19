package com.michalrubajczyk.reservations.service;

import com.michalrubajczyk.reservations.entity.User;
import com.michalrubajczyk.reservations.repository.UserRepository;
import com.michalrubajczyk.reservations.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public DefaultUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Username not found");

        return new MyUserDetails(user);
    }
}
