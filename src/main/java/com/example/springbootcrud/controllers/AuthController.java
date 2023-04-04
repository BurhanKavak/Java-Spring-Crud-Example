package com.example.springbootcrud.controllers;

import com.example.springbootcrud.dto.request.UserDtoForAuth;
import com.example.springbootcrud.dto.request.UserDtoForCreate;
import com.example.springbootcrud.dto.response.UserDtoResponse;
import com.example.springbootcrud.mapper.UserMapper;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.security.JwtTokenProvider;
import com.example.springbootcrud.service.CompanyService;
import com.example.springbootcrud.service.RoleService;
import com.example.springbootcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserMapper userMapper;





    @PostMapping("/login")
    public String login(@RequestBody UserDtoForAuth request) {
        User user = userService.findByFirstName(request.getFirstName());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getFirstName(),request.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Token : " + jwtToken;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> register(@RequestBody UserDtoForCreate request) {


        return new ResponseEntity<>(userService.createUser(request),HttpStatus.CREATED);
    }
}
