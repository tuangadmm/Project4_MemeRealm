package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.RegisterDto;
import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.services.AuthService;
import com.last.project4_memerealm.utils.JwtHelper;
import com.last.project4_memerealm.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	@Autowired
	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String login(String username, String password) {
		User u = userRepository.findByUsername(username);

		if(u == null) {
			return "Username not found";
		}else{
			if(!PasswordHelper.verifyPassword(password, u.getPassword())){
				return "Password is incorrect";
			}
		}
		return JwtHelper.create(username);
	}

	@Override
	public String register(RegisterDto obj) {
		if(userRepository.findByUsername(obj.username()) != null){
			return "Username is already taken";
		}

		if(!Objects.equals(obj.password(), obj.confirmPassword())){
			return "Passwords must be matched";
		}

		User u = new User();
		u.setUsername(obj.username());
		u.setPassword(PasswordHelper.hashPassword(obj.password()));
		u.setEmail(obj.email());

		userRepository.saveAndFlush(u);
		return "Register succeeded";
	}
}
