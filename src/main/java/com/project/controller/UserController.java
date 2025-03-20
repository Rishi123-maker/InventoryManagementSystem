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

@RestController
@RequestMapping("/register")
public class UserController {
    @Autowired
	private UserRepository userRepo;
    @Autowired
	private PasswordEncoder passwordEncoder;
    @PostMapping("/")
    public User register(@RequestBody User user)
    {
  	System.out.println("entered");
    	String password=passwordEncoder.encode(user.getPassword());
    	System.out.println(password);
    	User newUser=new User(user.getUsername(),password,"ROLE_"+user.getRole());
    	User dummy=userRepo.save(newUser);
    	System.out.println(userRepo.findByUsername(user.getUsername()).toString());
		return dummy;
    }
}
