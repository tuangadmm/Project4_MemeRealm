package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}
}
