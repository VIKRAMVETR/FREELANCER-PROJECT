package com.freelancenexus.userservice.dto;

import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
    
    @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters")
    private String fullName;
    
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phone;
    
    private String profileImageUrl;

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

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public UserUpdateDTO(
			@Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters") String fullName,
			@Size(max = 20, message = "Phone number must not exceed 20 characters") String phone,
			String profileImageUrl) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.profileImageUrl = profileImageUrl;
	}

	public UserUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}