package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // For password encryption
    }

    public String registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return "Username already exists! Try different Username";
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        System.out.println("User Registered Sucessfully!");
        return "User registered successfully!";
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("Password Match: " + passwordEncoder.matches(rawPassword, encodedPassword));

        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    abstract class UserRole {
        protected String roleName;

        public UserRole(String roleName) {
            this.roleName = roleName;
        }

        public abstract String getPermissions();
    }

   // regular users
    class RegularUser extends UserRole {
        public RegularUser() {
            super("Regular User");
        }

        @Override
        public String getPermissions() {
            return "View content only";
        }
    }


    class AdminUser extends UserRole {
        public AdminUser() {
            super("Admin User");
        }

        @Override
        public String getPermissions() {
            return "Full access: create, edit, delete";
        }
    }

    // Polymorphic method to demonstrate behavior
    public void printUserPermissions(UserRole userRole) {
        System.out.println(userRole.roleName + " has permissions: " + userRole.getPermissions());
    }


    public void Usage() {
        UserRole user1 = new RegularUser();
        UserRole user2 = new AdminUser();

        printUserPermissions(user1);
        printUserPermissions(user2);
    }
}
