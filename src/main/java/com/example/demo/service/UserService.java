package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.Users;
import com.example.demo.controller.DataController;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {


	@Autowired
	private UserRepository userRepo;

	public ResponseEntity<String> saveUser(Users newUser) {
		userRepo.save(newUser);
		return ResponseEntity.ok().body("User Sign-in Succes !!");
	}
	
	public ResponseEntity<Boolean> loginUser(String email, String password) {
		boolean isTrue = false;
		if (email.isEmpty() || password.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}
		System.out.println("email"+email);
		System.out.println("password"+password);
		isTrue = userRepo.existsByEmailAndPassword(email, password);
		return ResponseEntity.ok().body(isTrue);
	}
}
