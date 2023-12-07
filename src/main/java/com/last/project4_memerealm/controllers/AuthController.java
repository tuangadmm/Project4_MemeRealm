package com.last.project4_memerealm.controllers;

import com.last.project4_memerealm.models.dto.request.NewUserDto;
import com.last.project4_memerealm.models.dto.response.AuthResponse;
import com.last.project4_memerealm.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService as;

	@Autowired
	public AuthController(AuthService as) {
		this.as = as;
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@Valid @RequestBody NewUserDto obj, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {

			ArrayList<String> errors = new ArrayList<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}

		return new ResponseEntity<>(as.register(obj), HttpStatus.OK);
	}

	@PostMapping("login")
	public ResponseEntity<AuthResponse> login(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(as.login(obj),  HttpStatus.OK);
	}

	@PostMapping("validate-session")
	public ResponseEntity<Boolean> validateSession(@RequestBody Map<String, String> obj){
		return new ResponseEntity<>(as.validateSession(obj.get("token")),  HttpStatus.OK);
	}

	@PostMapping("logout")
	public ResponseEntity<Void> logout(@RequestBody Map<String, String> obj){
		as.logout(obj.get("token"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
