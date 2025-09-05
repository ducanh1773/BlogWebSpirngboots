package com.example.springboottestbuildserver.Repository;

import com.example.springboottestbuildserver.DTO.Request.LoginRequest;
import com.example.springboottestbuildserver.DTO.Response.LoginResponse;
import com.example.springboottestbuildserver.Model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users , Long> {
    Optional<Users> findByUsername(String username);
}
