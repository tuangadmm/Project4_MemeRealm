package com.last.project4_memerealm.controllers;

import com.last.project4_memerealm.models.User;
import com.last.project4_memerealm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

	private final UserService us;

	@Autowired
	public TestController(UserService us) {
		this.us = us;
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<>(us.getAll(), HttpStatus.OK);
	}




}
