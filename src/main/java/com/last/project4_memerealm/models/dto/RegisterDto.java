package com.last.project4_memerealm.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link com.last.project4_memerealm.models.User}
 */
public record RegisterDto(@NotNull @Size(max = 30) String username, @NotNull @Size(max = 250) String password,
                          @NotNull @Size(max = 250) String confirmPassword, @NotNull @Size(max = 100) String email) {
	public RegisterDto(String username, String password, String confirmPassword, String email) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}

	@Override
	public String username() {
		return username;
	}

	@Override
	public String password() {
		return password;
	}

	@Override
	public String email() {
		return email;
	}

	@Override
	public String confirmPassword() {
		return confirmPassword;
	}
}