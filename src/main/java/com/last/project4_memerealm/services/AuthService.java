package com.last.project4_memerealm.services;

import java.util.Map;

public interface AuthService {

	String login(Map<String, String> obj);

	String register(Map<String, String> obj);

	boolean validateSession(String token);

	void logout(String token);

	/**
	 * check for admin privilege
	 * @param token
	 * @return boolean
	 */
	boolean hasPermission(String token);


}
