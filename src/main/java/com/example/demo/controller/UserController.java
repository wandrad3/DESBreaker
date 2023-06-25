package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {

	UserService userService = new UserService();

	@GetMapping("/validUserPassword/")
	public ResponseEntity<String> validUSer(@RequestParam("user") String user,
			@RequestParam("password") String password) {
		User userJoao = new User();
		userJoao.setLogin(user);
		userJoao.setPassword(password);
		boolean isValidUser = userService.validateUser(userJoao);
		return isValidUser ? ResponseEntity.ok(user +" was logged successfully with password "+userJoao.getPassword())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User or Password");
	}

	@PostMapping("/redefinePassword/")
	public ResponseEntity<String> redefinePassword(@RequestParam("user") String user,
			@RequestParam("password") String password) {
		User userJoao = new User();
		userJoao.setLogin(user);
		userJoao.setPassword(password);
		String success  = userService.redefinePassword(userJoao);
		
		return ResponseEntity.ok(success);
	}
}
