package com.last.project4_memerealm.models.dto.response;

import lombok.Setter;

import java.util.List;

@Setter
public class LoginResponseDto {
	private String          message;
	private String          token;
	private List<String>    roles;
}
