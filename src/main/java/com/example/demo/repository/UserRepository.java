package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	Optional<Users> findById(Long id);
}
