package com.last.project4_memerealm.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserListBasicDto {
	private Integer userId;
	private String username;
	private String email;

}
