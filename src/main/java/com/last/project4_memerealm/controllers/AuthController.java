package com.last.project4_memerealm.controllers;

import com.last.project4_memerealm.models.dto.RegisterDto;
import com.last.project4_memerealm.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService as;

	@Autowired
	public AuthController(AuthService as) {
		this.as = as;
	}

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody RegisterDto obj){
		return new ResponseEntity<>(as.register(obj), HttpStatus.OK);
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password){
		return new ResponseEntity<>(as.login(username, password),  HttpStatus.OK);
	}
}
