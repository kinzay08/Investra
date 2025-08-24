package com.example.demo.controller;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.model.User;
import com.example.demo.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Optional;
@CrossOrigin(origins = "http://127.0.0.1:5501")// Allow only frontend origin

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        String response = userService.registerUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        Optional<User> userOptional = userService.findByUsername(userDTO.getUsername());

        if (userOptional.isEmpty() || !userService.checkPassword(userDTO.getPassword(), userOptional.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials , Username or Password is wrong");
        }

        String token = jwtUtil.generateToken(userOptional.get().getUsername());
        return ResponseEntity.ok(token);
    }
}
