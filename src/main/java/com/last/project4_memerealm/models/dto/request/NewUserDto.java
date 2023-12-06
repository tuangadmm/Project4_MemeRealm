package com.last.project4_memerealm.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDto {

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	@NotBlank(message = "Confirm password is required")
	private String confirmPassword;

}
