package com.example.cjv_assignment2.services;


import com.example.cjv_assignment2.models.User;
import com.example.cjv_assignment2.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MongoTemplate template;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return repository.findAll();
    }

    //create a new user
    public User registerUser(User newUser) throws Exception {

        //String encodedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        //System.out.println(password);

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

//        Query query1 = new Query();
//        query1.addCriteria(Criteria.where("username").is(newUser.getUsername()));
//        Query query2 = new Query();
//        query2.addCriteria(Criteria.where("email").is(newUser.getEmail()));

        User foundUsername = repository.findByUsername(newUser.getUsername());
        User foundEmail = repository.findByEmail(newUser.getEmail());

//        System.out.println(foundUsername.getUsername());

        if(newUser.getFirstName() == ""){
            throw new Exception("Missing First Name");
        }
        if(newUser.getLastName() == ""){
            throw new Exception("Missing Last Name");
        }
        if(newUser.getEmail() == ""){
            throw new Exception("Missing Email");
        }
        if(newUser.getPassword() == ""){
            throw new Exception("Missing Password");
        }
        if(foundUsername != null){
            throw new Exception("Username already exists");
        }
        if(foundEmail != null){
            throw new Exception("Email already used");
        }
        if(newUser.getRole() == null){
            newUser.setRole("user");
        }
        repository.insert(newUser);
        return newUser;
    }

    //get user by id
    public Optional<User> getUser(String id) throws Exception {
        Optional<User> user = repository.findById(id);

        if(!user.isPresent()){
            throw new Exception("User with id "+id+ " Does Not Exist");
        }
        return user;
    }

//
//    //authentication
//    public String authenicateUser(String email, String password){
//
////        Query query = new Query();
////        query.addCriteria(Criteria.where("email").is(email));
//        // System.out.println();
//        // System.out.println(template.find(query, User.class));
//
//        String userPassword;
//        User foundUser = repository.findByEmail(email);
//
//        if(foundUser != null){
//            userPassword = foundUser.getPassword();
//            if(!BCrypt.checkpw(password, userPassword)){
//                return "Incorrect Credentials";
//            }
//        }else{
//            return "Email Does not Exists";
//        }
//        String uName = foundUser.getUsername();
//        return "Welcome " + uName;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = repository.findByUsername(username);
        String username1 = foundUser.getUsername();
        String password = foundUser.getPassword();
        return new org.springframework.security.core.userdetails.User(username1, password, new ArrayList<>());
    }
}
