package com.example.Collaborative_Planner.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Collaborative_Planner.JwtTokenProvider;
import com.example.Collaborative_Planner.Entities.UserEntity;
import com.example.Collaborative_Planner.Service.UserService;
import com.example.Collaborative_Planner.Utils.LoginDTO;
import com.example.Collaborative_Planner.Utils.RegisterDTO;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO dto) {
        UserEntity user = userService.loadUserForLogin(dto.getUsername(), dto.getPassword());
        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
