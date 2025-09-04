package com.example.springboottestbuildserver.Cọntroller;

import com.example.springboottestbuildserver.Model.Users;
import com.example.springboottestbuildserver.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<List<Users>> createUser(@RequestBody List<Users> usersList) {
        try {
            List<Users> createdUsers = usersService.creatUser(usersList);
            return new ResponseEntity<>(createdUsers, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<List<Users>> updateUser(@RequestBody List<Users> usersList) {
        try {
            List<Users> updatedUsers = usersService.updateUser(usersList);
            return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Users>> deleteUser(@RequestBody List<Users> usersList) {
        try {
            List<Users> deletedUsers = usersService.deleteUser(usersList);
            return new ResponseEntity<>(deletedUsers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = usersService.getAllUser(null);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

}
