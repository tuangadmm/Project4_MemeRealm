package com.last.project4_memerealm.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link com.last.project4_memerealm.models.User}
 */
public class RegisterDto {
	@NotNull
	@Size(max = 30)
	private final String username;
	@NotNull
	@Size(max = 250)
	private final String password;
	@NotNull
	@Size(max = 250)
	private final String confirmPassword;
	@NotNull
	@Size(max = 100)
	private final String email;

	public RegisterDto(String username, String password, String confirmPassword, String email) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
}