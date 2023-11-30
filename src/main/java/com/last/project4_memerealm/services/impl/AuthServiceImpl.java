package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.Role;
import com.last.project4_memerealm.models.Session;
import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.UserRole;
import com.last.project4_memerealm.repositories.SessionRepository;
import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.repositories.UserRoleRepository;
import com.last.project4_memerealm.services.AuthService;
import com.last.project4_memerealm.utils.JwtHelper;
import com.last.project4_memerealm.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository ur;
	private final SessionRepository sr;
	private final UserRoleRepository urr;


	@Autowired
	public AuthServiceImpl(UserRepository ur, SessionRepository sr, UserRoleRepository urr) {
		this.ur = ur;
		this.sr = sr;
		this.urr = urr;
	}

	@Override
	public String login(Map<String, String> obj) {
		String username = obj.get("username");
		String password = obj.get("password");
		List<String> roles = new ArrayList<>();

		User u = ur.findByUsername(username);

		if(u == null) {
			return "Username not found";
		}else{
			if(!PasswordHelper.verifyPassword(password, u.getPassword()))
				return "Password is incorrect";
		}

		//get user roles
		List<UserRole> rs = urr.findByUser_Id(u.getId());

		//add to claim
		for(UserRole r : rs){
			roles.add(
				r.getRole().getRoleName()
			);
		}

		//create token with username, roles and userid
		String token = JwtHelper.create(username, roles, u.getId());

		//add this login to new session
		Session s = new Session();
		s.setUser(u);
		s.setSessionToken(token);
		s.setExpireTime(
			Instant.now().plus(30, ChronoUnit.DAYS)
		);

		sr.saveAndFlush(s);

		return token;
	}

	@Override
	public String register(Map<String, String> obj) {
		String username         = obj.get("username");
		String password         = obj.get("password");
		String confirmPassword  = obj.get("confirmPassword");
		String email            = obj.get("email");

		if(ur.findByUsername(username) != null){
			return "Username is already taken";
		}

		if(!Objects.equals(password, confirmPassword)){
			return "Passwords must be matched";
		}

		User u = new User();
		u.setUsername(username);
		u.setPassword(PasswordHelper.hashPassword(password));
		u.setEmail(email);

		ur.saveAndFlush(u);
		return "Register succeeded";
	}

	/**
	 * Make sure the current user is valid
	 * @param token
	 * @return boolean
	 */
	@Override
	public boolean validateSession(String token) {
		return sr.existsBySessionToken(token) && JwtHelper.verify(token);
	}

	@Override
	public void logout(String token) {
		if(token != null)
			sr.findBySessionToken(token).ifPresent(sr::delete);
	}

	@Override
	public boolean hasPermission(String token) {
		String username = JwtHelper.getUsernameFormToken(token);
		if(username == null)
			return false;

		User u = ur.findByUsername(username);
		if(u == null)
			return false;

		List<UserRole> rolesOnDb    = urr.findByUser_Id(u.getId());

		for(UserRole ur : rolesOnDb){
			if(Objects.equals(ur.getRole().getRoleName(), "admin"))
				return true;
		}
		return false;
	}
}
