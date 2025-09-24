package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.Song;
import com.example.demo.repository.SongRepository;

@Service
public class AudioService {

	@Autowired
	private SongRepository songRepo;
	private static final String Upload_mediaFile_Dir = "D:/Spring Projects/RetroPlayer/src/main/resources/static/mp3_files/"; 
	private static final String Upload_imageFile_Dir = "D:/Spring Projects/RetroPlayer/src/main/resources/static/mp3_images/";
	
	
	public ResponseEntity<Song> saveAudio(MultipartFile mediaFile,  MultipartFile imageFile) {
		
		String newImageName = "";
		
		try {
			Path mediaPath = Paths.get(Upload_mediaFile_Dir + mediaFile.getOriginalFilename());
			Files.createDirectories(mediaPath.getParent());
			Files.write(mediaPath, mediaFile.getBytes());
			System.out.println(mediaFile.getOriginalFilename());
			System.out.println(imageFile.getOriginalFilename());
			
			String originalName = imageFile.getOriginalFilename();
			String extension = "";
			if (originalName != null && originalName.contains(".")) {
				extension = originalName.substring(originalName.lastIndexOf("."));
			}
			
			newImageName = removeExtension.clearExtension(mediaFile.getOriginalFilename()) + extension;
			System.out.println(newImageName);
			Path imagePath = Paths.get(Upload_imageFile_Dir + newImageName);
			Files.createDirectories(imagePath.getParent());
			Files.write(imagePath, imageFile.getBytes());
		} catch (Exception e) {
			
		}
		Song song = new Song();
		song.setSongName(mediaFile.getOriginalFilename());
		song.setSongPath(Upload_mediaFile_Dir);
		song.setImageName(newImageName);
		song.setImagePath(Upload_imageFile_Dir);
		
		if (song.getSongName() == null || song.getSongName().isEmpty()
				|| song.getSongPath() == null || song.getSongPath().isEmpty()
				|| song.getSongLanguage() == null || song.getSongLanguage().isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		Song songSaved = songRepo.save(song);
		
		return ResponseEntity.status(HttpStatus.OK).body(songSaved);
	}
}
