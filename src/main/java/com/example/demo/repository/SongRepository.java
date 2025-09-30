package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.Song;

public interface SongRepository extends JpaRepository<Song, Long>{

	@Query("SELECT s FROM Song s WHERE s.songName = :songName")
	Song fetchBySongName(@Param("songName") String songName);
	
	Optional<Song> findBySongName(String songName);
}
