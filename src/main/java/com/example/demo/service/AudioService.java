package com.example.demo.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	private static final String dB_mediaFile_Dir = "/mp3_files/";
	private static final String dB_imageFile_Dir = "/mp3_images/";
	private static final String Default_album = "local";
	
	public AudioService(SongRepository songRepo) {
        this.songRepo = songRepo;
    }
	
	public ResponseEntity<Song> saveAudio(MultipartFile mediaFile,  MultipartFile imageFile,
			String lang) {
		
		String newImageName = "";
		
		try {
			Path mediaPath = Paths.get(Upload_mediaFile_Dir + mediaFile.getOriginalFilename());
			Files.createDirectories(mediaPath.getParent());
			Files.write(mediaPath, mediaFile.getBytes());
			
			String originalName = imageFile.getOriginalFilename();
			String extension = "";
			if (originalName != null && originalName.contains(".")) {
				extension = originalName.substring(originalName.lastIndexOf("."));
			}
			
			newImageName = removeExtension.clearExtension(mediaFile.getOriginalFilename()) + extension;
			Path imagePath = Paths.get(Upload_imageFile_Dir + newImageName);
			Files.createDirectories(imagePath.getParent());
			Files.write(imagePath, imageFile.getBytes());
		} catch (Exception e) {
			
		}
		Song song = new Song();
		song.setSongName(mediaFile.getOriginalFilename());
		song.setSongPath(dB_mediaFile_Dir);
		song.setImageName(newImageName);
		song.setImagePath(dB_imageFile_Dir);
		song.setSongLanguage(lang);
		
		if (song.getSongName() == null || song.getSongName().isEmpty()
				|| song.getSongPath() == null || song.getSongPath().isEmpty()
				|| song.getSongLanguage() == null || song.getSongLanguage().isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		if (song.getAlbum() == null || song.getAlbum().isEmpty())
			song.setAlbum(Default_album);
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTime = now.format(formatter);
		song.setTimestamp(dateTime);
		
		Song songSaved = songRepo.save(song);
		
		return ResponseEntity.status(HttpStatus.OK).body(songSaved);
	}
	
	public ResponseEntity<Page<Song>> allAudioSummaries(Pageable pageable) {
		
		Page<Song> songsPage = songRepo.findAll(pageable);
		return ResponseEntity.ok(songsPage);
	}
	public ResponseEntity<Resource> selectByName(String audioName) {
		
		Song song = songRepo.fetchBySongName(audioName);
		File file = new File(Upload_mediaFile_Dir + song.getSongName());
		try {
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("audio/mpeg")).body(resource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(null);
	}
	public ResponseEntity<Song> selectedAudioInfo(String audioName) {
		
		Song songInfo = songRepo.fetchBySongName(audioName);
		
		return ResponseEntity.ok(songInfo);
	}
}
