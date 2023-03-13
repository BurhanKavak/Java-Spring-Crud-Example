package com.example.springbootcrud.controllers;

import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "User Rest Api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "User'ın hepsini getirir")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    @PostMapping()
    @ApiOperation(value = "Yeni User ekleme metodu", notes = "Bu metodu dikkatli kullan")
    public ResponseEntity<User> createUser(@ApiParam(value = "Burhan") @Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.updateUser(newUser,userId),HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

