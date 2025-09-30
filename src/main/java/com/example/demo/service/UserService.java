package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LastPlayed;
import com.example.demo.DTO.Song;
import com.example.demo.DTO.Users;
import com.example.demo.repository.LastPlayedRepository;
import com.example.demo.repository.SongRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {


	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SongRepository songRepo;
	
	@Autowired
	private LastPlayedRepository lastPlayedRepo;
	
	public ResponseEntity<String> saveUser(Users newUser) {
		userRepo.save(newUser);
		return ResponseEntity.ok().body("User Sign-in Succes !!");
	}
	
	public Optional<Users> loginUser(String email, String password) {
		
		if (email.isEmpty() || password.isEmpty()) {
			throw new NullPointerException("Email or password is Empty");
		}
		System.out.println("email"+email);
		System.out.println("password"+password);
		 
		return userRepo.findByEmailAndPassword(email, password);
	}
	public Optional<Users> getUserById(Long id) {
		return userRepo.findById(id);
	}
	
	@Transactional
	public ResponseEntity<String> lastPlayed(Long userId, String lastSong) {
		
		if (userId == null) {
			System.out.println("Null found");
	        // reject null user id
	        return ResponseEntity.status(401).body("User not logged in");
	    }
		
		Optional<Song> songOpt = songRepo.findBySongName(lastSong);
		
		if (songOpt.isEmpty()) 
			return ResponseEntity.badRequest().body("Song Not Found");
		
		Optional<Users> userOpt = userRepo.findById(userId);
		
		if (userOpt.isEmpty())
			return ResponseEntity.badRequest().body("User Not Found");
		
		Users user = userOpt.get();
		Song song = songOpt.get();
		
		Optional<LastPlayed> lastPlayedOpt = lastPlayedRepo.findByUser(user);
		
		if (lastPlayedOpt.isPresent()) {
			
			LastPlayed lastplayed = lastPlayedOpt.get();
			lastplayed.setSong(song);
			lastPlayedRepo.save(lastplayed);
		}else {
			LastPlayed lastPlayed = new LastPlayed();
			lastPlayed.setSong(songOpt.get());
			lastPlayed.setUser(userOpt.get());
			
			lastPlayedRepo.save(lastPlayed);
		}
		return ResponseEntity.ok("Saved Success");
	}
}
