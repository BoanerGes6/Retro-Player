package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	boolean existsByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
