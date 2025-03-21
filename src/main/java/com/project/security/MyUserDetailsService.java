package com.project.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.project.exception.ResourceNotFoundException;
import com.project.entity.User;
import com.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
   
  
    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
    	
    	User user=null;
    	
    	user =  userRepository.findByUsername(username);
    	
    	System.out.println(user);
        if (user == null) {
            throw new  ResourceNotFoundException("User has not been found");
        }
         
        // Convert roles to Spring Security's GrantedAuthority format
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().toUpperCase()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
               user.getPassword(),  // Password must be encoded (BCrypt)
                authorities
        );
		
    }
}
