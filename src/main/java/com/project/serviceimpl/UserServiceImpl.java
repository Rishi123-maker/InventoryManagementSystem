package com.project.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dto.LoginDto;
import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.security.JwtUtil;
import com.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	  private  AuthenticationManager authenticationManager;
	@Autowired
	private  JwtUtil jwtUtil;
	@Override
	public User register(User user) {
		System.out.println(user.getPassword());
		String password = passwordEncoder.encode(user.getPassword());
		User newUser = new User(user.getUsername(), password, "ROLE_CUSTOMER");
		User dummy = userRepo.save(newUser);
		System.out.println(dummy);
		return dummy;
		
	}

	@Override
	public String login(LoginDto loginRequest) throws Exception {
		 String token ="";
		 System.out.println(loginRequest.getUsername()+" "+loginRequest.getPassword());
			try {
	            // Authenticate the user
	            org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            loginRequest.getUsername(),
	                            loginRequest.getPassword()
	                    )
	            );
	            
	            // Generate JWT token
	            token = jwtUtil.generateToken(authentication.getName(),userRepo.findByUsername(loginRequest.getUsername()).getId());

	            // Return the token in the response body
	          
	        } catch (Exception e) {
	            // Handle incorrect credentials
	           throw new Exception("Invalid Cedentials");
	        }
			return token;
	}

}
