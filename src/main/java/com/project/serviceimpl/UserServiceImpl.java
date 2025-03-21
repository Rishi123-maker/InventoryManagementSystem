package com.project.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
    @Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User register(User user) {
		System.out.println(user.toString());
    	String password=passwordEncoder.encode(user.getPassword());
    	System.out.println(password);
    	User newUser=new User(user.getUsername(),password,"ROLE_"+user.getRole());
    	User dummy=userRepo.save(newUser);
    	System.out.println(userRepo.findByUsername(user.getUsername()).toString());
		
    	return dummy;
		
		
	}
	
}
