package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
			@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("lang") String language) {
		
		return audioService.saveAudio(mediaFile, imageFile, language);
	}
	
	@PostMapping("/audioSearch")
	public void searchAudioName() {
		
	}
	
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
	public ResponseEntity<Song> selectedAudioInfo(@RequestParam("query") String audioName) {
		return audioService.selectedAudioInfo(audioName);
	}
	
	@GetMapping("/allSongs")
	public ResponseEntity<Page<Song>> getAllAudio(Pageable pageable) {
		
		return audioService.allAudioSummaries(pageable);
	}
	
}
