package com.example.springboottestbuildserver.Service;

import com.example.springboottestbuildserver.Configuration.JwtUtil;
import com.example.springboottestbuildserver.DTO.Request.LoginRequest;
import com.example.springboottestbuildserver.DTO.Response.LoginResponse;
import com.example.springboottestbuildserver.Model.Users;
import com.example.springboottestbuildserver.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    // registry
    public List<Users> creatUser(List<Users> usersList) {

        List<Users> usersListCreate = new LinkedList();
        for (Users users : usersList) {
            if (users.getUsername().isBlank() || users.getEmail().isBlank() || users.getPassword().isBlank()) {
                throw new RuntimeException("field all");
            }
            String passwordHash = passwordEncoder.encode(users.getPassword());
            users.setPassword(passwordHash);
            usersListCreate.add(users);
        }
        userRepository.saveAll(usersListCreate);


        return usersListCreate;
    }

    //login
    public LoginResponse login(LoginRequest loginRequests) {
        Optional<Users> users = userRepository.findByUsername(loginRequests.getUsername());
        Users usersCheck = users.get();
        if (users.isEmpty()) {
            throw new RuntimeException("Cannot find username");
        }
        boolean checkPassword = false;
        checkPassword = passwordEncoder.matches(loginRequests.getPassword(), usersCheck.getPassword());
        if (checkPassword == false) {
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtUtil.generateToken(usersCheck.getUsername());
        LoginResponse loginResponse = new LoginResponse(usersCheck.getUsername(), true, token);
        return loginResponse;
    }
}
