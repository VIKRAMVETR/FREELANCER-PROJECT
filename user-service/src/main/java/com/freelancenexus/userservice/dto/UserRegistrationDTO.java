package com.freelancenexus.userservice.dto;

import com.freelancenexus.userservice.model.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters")
    private String fullName;
    
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phone;
    
    @NotNull(message = "Role is required")
    private UserRole role;
    
    private String profileImageUrl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public UserRegistrationDTO(
			@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String password,
			@NotBlank(message = "Full name is required") @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters") String fullName,
			@Size(max = 20, message = "Phone number must not exceed 20 characters") String phone,
			@NotNull(message = "Role is required") UserRole role, String profileImageUrl) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.phone = phone;
		this.role = role;
		this.profileImageUrl = profileImageUrl;
	}

	public UserRegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}