package com.last.project4_memerealm.services;

import com.last.project4_memerealm.models.dto.RegisterDto;

public interface AuthService {
	String login(String username, String password);
	String register(RegisterDto obj);
}
