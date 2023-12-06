package com.last.project4_memerealm.controllers;

import com.last.project4_memerealm.repositories.UserRepository;
import com.last.project4_memerealm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

	private final UserService us;
	private final UserRepository userRepository;

	@Autowired
	public TestController(UserService us, UserRepository userRepository) {
		this.us = us;
		this.userRepository = userRepository;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>(us.testGetDetail(), HttpStatus.OK);
//		return new ResponseEntity<>(userRepository.getUserDetailsRaw(1004), HttpStatus.OK);
	}




}
