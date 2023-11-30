package com.last.project4_memerealm.models.dto.response;

import com.last.project4_memerealm.models.UserRole;
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

	private List<UserRole>  roles;

	private Integer     postCount;
	private Integer     favouriteCount;
	private Integer     followCount;
	private Integer     followerCount;

}
