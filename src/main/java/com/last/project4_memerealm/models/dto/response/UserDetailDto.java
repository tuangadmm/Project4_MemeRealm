package com.last.project4_memerealm.models.dto.response;

import com.last.project4_memerealm.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {

	private Integer     userId;
	private String      username;
	private String      email;
	private String      avatar;

	List<Role>          roles;

	private Long        postCount;
	private Long        favouriteCount;
	private Long        followCount;

}
