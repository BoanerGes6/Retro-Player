package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.Song;
import com.example.demo.DTO.Users;
import com.example.demo.service.AudioService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class DataController {

	@Autowired
	private AudioService audioService;
	
	@PostMapping("/getAudio")
	public ResponseEntity<Song> reciveAudioName(@RequestParam("mediaFile") MultipartFile mediaFile, 
			@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("lang") String language) {
		
		return audioService.saveAudio(mediaFile, imageFile, language);
	}
	
	@PostMapping("/audioSearch")
	public void searchAudioName() {
		
	}
	
	
	/*-----------------------------Sign-In-------------------------------*/
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> Signin(@RequestBody Users newUser) {
		
		return userService.saveUser(newUser);
	}
	
	/*-------------------------------Log-In--------------------------------*/
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session) {
		
		Optional<Users> user = userService.loginUser(email, password);
		
		Map<String, Object> response = new HashMap<>();
		if (user.isPresent()) {
			session.setAttribute("userId", user.get().getId());
			response.put("success", true);
			response.put("id", user.get().getId());
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/isLoggedIn")
	public Map<String, Object> isLoggedIn(HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		Object userId =  session.getAttribute("userId");
		response.put("loggedIn", userId != null);
		if (userId != null) response.put("userId", userId);
		return response;
	}
	
	@PostMapping("/logOut")
	public ResponseEntity<String> logOut(HttpSession session) {
		session.invalidate();
		
		return ResponseEntity.ok("Logged Out Success !!");
	}
	
	/*-----------------------------Log-In Ends--------------------------------*/
	
	@GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
        // This method returns a successful HTTP response with no body (204 No Content),
        // effectively telling the browser to stop looking for a favicon.
    }
	
	@GetMapping("/selectedSong")
	public ResponseEntity<Resource> selectedAudio(@RequestParam("query") String audioName) {
		System.out.println("Selected Audio Name : "+audioName);
		
		return audioService.selectByName(audioName);
	}
	
	@GetMapping("/selectedSongInfo")
	public ResponseEntity<Resource> selectedAudioInfo(@RequestParam("query") String audioName) {
		return audioService.selectedAudioInfo(audioName);
	}
	
	@GetMapping("/allSongs")
	public ResponseEntity<Page<Song>> getAllAudio(Pageable pageable) {
		
		return audioService.allAudioSummaries(pageable);
	}
	
	@GetMapping("/autoplayNextSong")
	public void autoplayNextSong(@RequestParam("query") String currentAudio) {
		
		audioService.nxtSong(currentAudio);
		return;
	}
	// ----------------------Last Played song section ------------------------//
	@GetMapping("/lastPlayedSong")
	public ResponseEntity<String> lastPlayedSong(@RequestParam("query") String lastSong, 
			HttpSession session) {
		
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
	        return ResponseEntity.status(401).body("User not logged in");
	    }
		
		return userService.lastPlayed(userId, lastSong);
	}
	
	@GetMapping("/loadLastPlayed")
	public void loadLastPlayed(HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		userService.loadLastPlayedSong(userId);
	}
	
}
