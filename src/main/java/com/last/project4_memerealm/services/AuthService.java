package com.last.project4_memerealm.services;

import com.last.project4_memerealm.models.dto.request.NewUserDto;
import com.last.project4_memerealm.models.dto.response.AuthResponse;

import java.util.List;
import java.util.Map;

public interface AuthService {

	AuthResponse login(Map<String, String> obj);

	String register(NewUserDto obj);

	boolean validateSession(String token);

	void logout(String token);

	/**
	 * check if user has privilege
	 * @param token
	 * @return boolean
	 */
	boolean hasPermission(String token, List<String> roles);


}
