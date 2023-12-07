package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.request.NewUserDto;
import com.last.project4_memerealm.models.dto.response.UserListBasicDto;
import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.services.AdminService;
import com.last.project4_memerealm.services.AuthService;
import com.last.project4_memerealm.utils.PasswordHelper;
import com.last.project4_memerealm.utils.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	private final List<String>    access = Permissions.ADMIN;

	private final UserRepository ur;
	private final AuthService as;


	@Autowired
	public AdminServiceImpl(UserRepository ur, AuthService as) {
		this.ur = ur;
		this.as = as;
	}


	@Override
	public Page<UserListBasicDto> getUsersList(String token, Integer pageIndex) {
		if(!as.hasPermission(token, access))
			return null;

		Pageable pageable = PageRequest.of(pageIndex, 10);

		return ur.findAllUsers(pageable);
	}

	@Override
	public boolean deleteUser(String token, Integer userId) {
		if(!as.hasPermission(token, access))
			return false;

		ur.findById(userId).ifPresent(ur::delete);

		return true;
	}

	@Override
	public String createUser(String token, NewUserDto obj) {
		if(!as.hasPermission(token, access))
			return "No access";


		if(ur.findByUsername(obj.getUsername()) != null )
			return "Username is already taken";

		if(obj.getPassword() != obj.getConfirmPassword())
			return "Password must be matched";

		User u = new User();
		u.setUsername(obj.getUsername());
		u.setPassword(PasswordHelper.hashPassword(obj.getPassword()));
		u.setEmail(obj.getEmail());

		ur.saveAndFlush(u);
		return "Add user succeeded";
	}

	@Override
	public boolean updateUser(String token, User obj) {
		if(!as.hasPermission(token, access))
			return false;

		User u = ur.findByUsername(obj.getUsername());
		u.setEmail(obj.getEmail());
		u.setPassword(obj.getPassword());
		u.setAvatar(obj.getAvatar());
		ur.saveAndFlush(u);

		return true;
	}
}
