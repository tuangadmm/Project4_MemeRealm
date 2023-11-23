package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.RegisterDto;
import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.services.AuthService;
import com.last.project4_memerealm.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;

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
		return "token will be here";
	}

	@Override
	public String register(RegisterDto obj) {
		if(userRepository.findByUsername(obj.getUsername()) != null){
			return "Username is already taken";
		}

		if(!Objects.equals(obj.getPassword(), obj.getConfirmPassword())){
			return "Passwords must be matched";
		}

		User u = new User();
		u.setUsername(obj.getUsername());
		u.setPassword(PasswordHelper.hashPassword(obj.getPassword()));
		u.setEmail(obj.getEmail());

		userRepository.saveAndFlush(u);
		return "Register succeeded";
	}
}
