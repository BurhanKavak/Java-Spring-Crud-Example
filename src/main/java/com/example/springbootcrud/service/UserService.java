package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.request.UserDtoForCreate;
import com.example.springbootcrud.dto.request.UserDtoForUpdate;
import com.example.springbootcrud.dto.response.UserDtoResponse;
import com.example.springbootcrud.model.User;

import java.util.List;

public interface UserService {

    List<UserDtoResponse> getUsers();

    UserDtoResponse getUser(Long userId);

    UserDtoResponse createUser(UserDtoForCreate userDtoForCreate);

    User updateUser(UserDtoForUpdate userDtoForUpdate, Long userId);

    void deleteUser(Long userId);

    User findByFirstName(String firstName);


    User createUserToAuth(User user);


}
