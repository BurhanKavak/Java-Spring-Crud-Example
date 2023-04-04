package com.example.springbootcrud.service.impl;

import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepository;
import com.example.springbootcrud.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(username);
        return JwtUserDetails.create(user);
    }


    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return JwtUserDetails.create(user);
    }
}
