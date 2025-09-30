package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.DTO.Users;
import com.example.demo.service.UserService;

@Controller
public class ApkController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/Admin")
	public String admin() {
		return "Admin";
	}
	
	@GetMapping("/AudioPlayer")
	public String AudioPlayer() {
		return "AudioPlayer";
	}
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/AudioPlayer/{id}")
	public String PlayerPage(@PathVariable Long id, Model model) {
		Optional<Users> user = userService.getUserById(id);
		if (user.isPresent()) {
			model.addAttribute("userName", user.get().getUserName());
			return "AudioPlayer";
		} else {
			return "redirect:/Login";
		}
	}
	
	@GetMapping("/Sign_up")
	public String SignUp() {
		return "Sign_up";
	}
	
	@GetMapping("/Login")
	public String logIn() {
		return "Login";
	}
}
