package com.example.springboottestbuildserver.Cọntroller;

import com.example.springboottestbuildserver.DTO.Request.LoginRequest;
import com.example.springboottestbuildserver.DTO.Response.LoginResponse;
import com.example.springboottestbuildserver.Model.Users;
import com.example.springboottestbuildserver.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authen")
public class AuthenticationControiller {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/registry")
    public ResponseEntity<List<Users>> creatUser(@RequestBody List<Users> usersList) {

        return ResponseEntity.ok(authenticationService.creatUser(usersList));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequests) {
        return ResponseEntity.ok(authenticationService.login(loginRequests));
    }




}
