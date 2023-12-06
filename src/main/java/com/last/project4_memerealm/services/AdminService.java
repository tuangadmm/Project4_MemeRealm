package com.last.project4_memerealm.services;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.response.UserListBasicDto;

import java.util.List;

public interface AdminService {
	List<UserListBasicDto> getUsersList(Integer pageIndex);
	boolean deleteUser(String token, Integer userId);
	boolean createUser(String token, User obj);
	boolean updateUser(String token, User obj);

}
