package com.project.service;

import com.project.dto.LoginDto;
import com.project.entity.User;

public interface UserService {
	
	User register(User user);

	String login(LoginDto loginRequest) throws Exception;
}
