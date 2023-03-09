package com.example.springbootcrud.service.abs;

import com.example.springbootcrud.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUser(Long userId);

    User createUser(User user);

    User updateUser(User newUser, Long userId);

    void deleteUser(Long userId);



}
