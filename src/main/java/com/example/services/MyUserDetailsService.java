package com.example.services;

import com.example.repositories.UserRepository;
import com.example.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        var user = userRepository.findByUsername(username);
        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
