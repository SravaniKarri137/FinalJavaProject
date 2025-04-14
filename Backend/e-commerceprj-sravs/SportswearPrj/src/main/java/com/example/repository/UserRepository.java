package com.example.repository;



import com.example.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

	Object findByEmail(String email);

	
    
}

