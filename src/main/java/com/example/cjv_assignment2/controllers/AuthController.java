package com.example.cjv_assignment2.controllers;


import com.example.cjv_assignment2.CustomizedResponse;
import com.example.cjv_assignment2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
@CrossOrigin(origins = {"http://localhost:3000", "https://cjv-final-movie.herokuapp.com"})
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value="/login", consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            CustomizedResponse response = new CustomizedResponse("Welcome "+ user.getUsername() , null);

            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch(Exception e) {
            CustomizedResponse response = new CustomizedResponse("Incorrect Credentials", null);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }
    }

}
