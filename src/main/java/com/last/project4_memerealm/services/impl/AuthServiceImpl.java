package com.last.project4_memerealm.services.impl;

import com.last.project4_memerealm.models.Role;
import com.last.project4_memerealm.models.Session;
import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.request.NewUserDto;
import com.last.project4_memerealm.models.dto.response.AuthResponse;
import com.last.project4_memerealm.repositories.RoleRepository;
import com.last.project4_memerealm.repositories.SessionRepository;
import com.last.project4_memerealm.repositories.UserRepository;
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
	private final RoleRepository rr;


	@Autowired
	public AuthServiceImpl(UserRepository ur, SessionRepository sr, RoleRepository rr) {
		this.ur = ur;
		this.sr = sr;
		this.rr = rr;
	}

	@Override
	public AuthResponse login(Map<String, String> obj) {
		String username = obj.get("username");
		String password = obj.get("password");

		List<String> roles = new ArrayList<>();
		AuthResponse res = new AuthResponse();

		User u = ur.findByUsername(username);

		if(u == null) {
			res.setMessage("Username not found");
			return res;
		}else{
			if(!PasswordHelper.verifyPassword(password, u.getPassword())) {
				res.setMessage("Password is incorrect");
				return res;
			}
		}

		//get user roles
		List<Role> rs = rr.findByUserRoles_User_Id(u.getId());

		//add to claim
		for(Role r : rs){
			roles.add(
				r.getRoleName()
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

		//update response
		res.setToken(token);
		res.setRoles(roles);
		res.setMessage("Success");

		return res;
	}

	@Override
	public String register(NewUserDto obj) {
		String username         = obj.getUsername();
		String password         = obj.getPassword();
		String confirmPassword  = obj.getConfirmPassword();
		String email            = obj.getEmail();


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
	public boolean hasPermission(String token, List<String> roles) {
		boolean grant = false;

		//validate token
		String username = JwtHelper.getUsernameFormToken(token);
		if(username == null)
			return false;

		//validate user
		User u = ur.findByUsername(username);
		if(u == null)
			return false;

		//get user's roles
		List<Role> rolesOnDb    = rr.findByUserRoles_User_Id(u.getId());

		//check if role matched database
		for(Role r : rolesOnDb){
			if(roles.contains(r.getRoleName())) {
				grant = true;
				break;
			}
		}
		return grant;
	}
}
