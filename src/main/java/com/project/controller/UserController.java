package com.project.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.LoginDto;
import com.project.entity.User;
import com.project.security.JwtUtil;
import com.project.service.UserService;

@RestController
@RequestMapping
public class UserController {
	@Autowired
	private UserService userService;
	@CrossOrigin(origins="*")
	@PostMapping("/register/")
	public User register(@RequestBody User user) throws Exception {
		return userService.register(user);
	}
	@CrossOrigin(origins="*")
	@PostMapping("/login/")
	public ResponseEntity<String>Login(@RequestBody LoginDto loginRequest) throws Exception{
		
		return new ResponseEntity<String> (userService.login(loginRequest),HttpStatus.OK);
	}
}
