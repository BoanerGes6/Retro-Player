package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.Song;
import com.example.demo.service.AudioService;

@RestController
public class DataController {

	@Autowired
	private AudioService audioService;
	
	@PostMapping("/getAudio")
	public ResponseEntity<Song> reciveAudioName(@RequestParam("mediaFile") MultipartFile mediaFile, 
			@RequestParam("imageFile") MultipartFile imageFile) {
		
		return audioService.saveAudio(mediaFile, imageFile);
	}
	
	@PostMapping("/audioSearch")
	public void searchAudioName() {
		
	}
	
}
