package com.freelancenexus.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancenexus.userservice.dto.LoginResponseDTO;
import com.freelancenexus.userservice.dto.UserLoginDTO;
import com.freelancenexus.userservice.dto.UserRegistrationDTO;
import com.freelancenexus.userservice.dto.UserResponseDTO;
import com.freelancenexus.userservice.dto.UserUpdateDTO;
import com.freelancenexus.userservice.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/users")
public class UserController {
	private final Logger log= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// Public endpoints - No authentication required
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        log.info("Received registration request for email: {}", registrationDTO.getEmail());
        UserResponseDTO response = userService.registerUser(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        log.info("Received login request for email: {}", loginDTO.getEmail());
        LoginResponseDTO response = userService.loginUser(loginDTO);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header(HttpHeaders.SET_COOKIE, response.getAccessToken().toString())
                .body(response);
    }
    
    // Authenticated user endpoints
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDTO> getCurrentUserProfile() {
        log.info("Received request to get current user profile");
        UserResponseDTO response = userService.getCurrentUserProfile();
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDTO> updateCurrentUserProfile(@Valid @RequestBody UserUpdateDTO updateDTO) {
        log.info("Received request to update current user profile");
        UserResponseDTO response = userService.updateCurrentUserProfile(updateDTO);
        return ResponseEntity.ok(response);
    }
    
    // Any authenticated user can view other users
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT', 'FREELANCER')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        log.info("Received request to get user by ID: {}", id);
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    // Admin only endpoints
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        log.info("Received request to get all users");
        List<UserResponseDTO> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Received request to delete user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}