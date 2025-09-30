package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.DTO.LastPlayed;
import com.example.demo.DTO.Users;

public interface LastPlayedRepository extends JpaRepository<LastPlayed, Long>{

	Optional<LastPlayed> findByUser(Users user);
}
