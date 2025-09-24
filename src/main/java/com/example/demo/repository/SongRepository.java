package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.DTO.Song;

public interface SongRepository extends JpaRepository<Song, Long>{

}
