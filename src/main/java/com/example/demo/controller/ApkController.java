package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
