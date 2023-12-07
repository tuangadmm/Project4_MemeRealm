package com.last.project4_memerealm.controllers;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.models.dto.request.NewUserDto;
import com.last.project4_memerealm.models.dto.response.UserListBasicDto;
import com.last.project4_memerealm.services.AdminService;
import com.last.project4_memerealm.services.UserService;
import com.last.project4_memerealm.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final AdminService as;
	private final UserService us;

	@Autowired
	public AdminController(AdminService as, UserService us) {
		this.as = as;
		this.us = us;
	}

	@PostMapping("/user-list")
	public ResponseEntity<Page<UserListBasicDto>> userListPaged(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(as.getUsersList(obj.get("token"), Integer.valueOf(obj.get("page_index"))), HttpStatus.OK);
	}

	@PostMapping("/delete-user")
	public ResponseEntity<Boolean> deleteUser(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(as.deleteUser(obj.get("token"), Integer.valueOf(obj.get("user_id"))), HttpStatus.OK);
	}

	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody Map<String, String> obj){
		NewUserDto u = new NewUserDto();

		u.setUsername(obj.get("username"));
		u.setEmail(obj.get("email"));
		u.setPassword(obj.get("password"));
		u.setConfirmPassword(obj.get("confirm_password"));

		return new ResponseEntity<>(as.createUser(obj.get("token"), u), HttpStatus.OK);
	}

	@PostMapping("/update-user")
	public ResponseEntity<Boolean> updateUser(@RequestBody Map<String, String> obj){

		User u = new User();
		u.setId(Integer.valueOf(obj.get("user_id")));
		u.setUsername(obj.get("username"));
		u.setEmail(obj.get("email"));
		u.setPassword(obj.get("password"));
		u.setAvatar(obj.get("avatar"));

		return new ResponseEntity<>(us.updateInformation(obj.get("token"), u ), HttpStatus.OK);
	}

}
