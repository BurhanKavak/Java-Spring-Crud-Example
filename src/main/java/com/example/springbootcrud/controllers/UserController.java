package com.example.springbootcrud.controllers;

import com.example.springbootcrud.dto.request.UserDtoForCreate;
import com.example.springbootcrud.dto.request.UserDtoForUpdate;
import com.example.springbootcrud.dto.response.UserDtoResponse;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test/list")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserDtoResponse>> getUsers() {
        List<UserDtoResponse> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDtoResponse> getUser(@PathVariable("userId") Long userId) {
        UserDtoResponse userDtoResponse = userService.getUser(userId);
        return ResponseEntity.ok(userDtoResponse);
    }


//    @PostMapping()
//    public ResponseEntity<UserDtoResponse> createUser(@Valid @RequestBody UserDtoForCreate userDtoForCreate) {
//        return new ResponseEntity<>(userService.createUser(userDtoForCreate), HttpStatus.CREATED);
//    }

    @PutMapping("/{userId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDtoForUpdate userDtoForUpdate, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.updateUser(userDtoForUpdate,userId),HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{userId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

