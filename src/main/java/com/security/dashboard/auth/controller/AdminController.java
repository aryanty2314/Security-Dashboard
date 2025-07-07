package com.security.dashboard.auth.controller;

import com.security.dashboard.auth.Roles;
import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.entity.User;
import com.security.dashboard.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/get-all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id).get();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/get-user-by-email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        // Optionally, you can return the deleted user or a success message
        return new ResponseEntity<>(user.get().getName()+ "Successfully Deleted" , HttpStatus.OK);
    }

    @PostMapping("/add-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addAdmin(@RequestBody RegisterRequest request) {
        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // User already exists
        }
        // Create a new user with ADMIN role
        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
// Ensure password is hashed in service layer
                .role(Roles.ADMIN) // Set role to ADMIN
                .twoFactorEnabled(false) // Default to false, can be updated later
                .build();

        User savedUser = userRepository.save(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/admin-dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminDashboard()
    {
        // This endpoint can be used to return any admin-specific data or statistics
        return new ResponseEntity<>("Welcome to the Admin Dashboard", HttpStatus.OK);
    }
}