package com.project.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username); // Find user by username
}

