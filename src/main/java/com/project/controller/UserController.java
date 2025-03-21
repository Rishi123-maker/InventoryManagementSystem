package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.service.UserService;
import com.project.serviceimpl.UserServiceImpl;
@RestController
@RequestMapping
public class UserController {
	@Autowired
	private UserService userService;
    
    @PostMapping("/register/")
    public User register(@RequestBody User user)
    {
  return userService.register(user);
		
    }
}
