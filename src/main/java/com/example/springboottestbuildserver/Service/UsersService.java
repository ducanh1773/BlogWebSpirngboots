package com.example.springboottestbuildserver.Service;

import com.example.springboottestbuildserver.Model.Users;
import com.example.springboottestbuildserver.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;

//    public List<Users> creatUser(List<Users> usersList) {
//        List<Users> usersListCreate = new LinkedList();
//        for (Users users : usersList) {
//            if (users.getUsername().isBlank() || users.getEmail().isBlank() || users.getPassword().isBlank()) {
//                throw new RuntimeException("field all");
//            }
//            usersListCreate.add(users);
//        }
//        userRepository.saveAll(usersListCreate);
//        return usersListCreate;
//    }

    public List<Users> updateUser(List<Users> usersList) {
        List<Users> usersListUpdate = new LinkedList();
        for (Users users : usersList) {
            if (users.getId().equals(null) || users.getUsername().isBlank() || users.getEmail().isBlank() || users.getPassword().isBlank()) {
                throw new RuntimeException("field all");
            }
            usersListUpdate.add(users);
        }
        userRepository.saveAll(usersListUpdate);
        return usersListUpdate;
    }

    public List<Users> deleteUser(List<Users> usersList) {
        List<Long> usersListDelete = new LinkedList();
        for (Users users : usersList) {
            if (users.getId().equals(null) || users.getUsername().isBlank() || users.getEmail().isBlank() || users.getPassword().isBlank()) {
                throw new RuntimeException("field all");
            }            usersListDelete.add(users.getId());
        }
        userRepository.deleteAllById(usersListDelete);
        return usersList;
    }

    public List<Users> getAllUser(List<Users> usersList) {
        return userRepository.findAll();
    }


}
