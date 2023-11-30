package com.last.project4_memerealm.services;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.response.UserDetailDto;

import java.util.List;

public interface AdminService {
	List<UserDetailDto> getUsersList(String token);
	boolean deleteUser(String token, Integer userId);
	boolean createUser(String token, User obj);

}
