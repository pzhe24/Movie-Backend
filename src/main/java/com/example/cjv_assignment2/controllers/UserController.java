package com.example.cjv_assignment2.controllers;

import com.example.cjv_assignment2.CustomizedResponse;
import com.example.cjv_assignment2.models.User;
import com.example.cjv_assignment2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/users")
    public ResponseEntity getUsers(){
        CustomizedResponse customizedResponse = new CustomizedResponse("Getting all users", service.getUsers());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //user registration
    @PostMapping(value="/register", consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity userRegistration(@RequestBody User user) {
        CustomizedResponse customizedResponse = null;
        try{
            service.registerUser(user);
            customizedResponse = new CustomizedResponse("User Registered Successfully", Collections.singletonList(service.getUser(user.getId())));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.CONFLICT);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") String id){
        CustomizedResponse customizedResponse= null;

        try{
            customizedResponse = new CustomizedResponse("Getting a single user with id: "+id, Collections.singletonList(service.getUser(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }

    //authenticate a user
//    @PostMapping(value="/login", consumes={
//            MediaType.APPLICATION_JSON_VALUE
//    })
//    public ResponseEntity login(@RequestBody User user){
//        return new ResponseEntity(service.authenicateUser(user.getEmail(), user.getPassword()), HttpStatus.ACCEPTED);
//    }

}
