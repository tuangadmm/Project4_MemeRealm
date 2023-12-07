package com.last.project4_memerealm.services;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.request.NewUserDto;
import com.last.project4_memerealm.models.dto.response.UserListBasicDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {
	Page<UserListBasicDto> getUsersList(String token, Integer pageIndex);
	boolean deleteUser(String token, Integer userId);
	String createUser(String token, NewUserDto obj);
	boolean updateUser(String token, User obj);

}
