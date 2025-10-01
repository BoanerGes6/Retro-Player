<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retro Player/ MP3 Player</title>
<link rel="stylesheet" href="/css/nav.css">
<link rel="stylesheet" href="/css/AudioPlayer.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
	<nav class="navContainer">
		<div class="logo" onclick="window.location.href='/index'"></div>
		<div class="userNameDisplay"><span>${userName}</span></div>
		<div class="profile">
			<span><i class="fa-solid fa-user-tie"></i></span>
			<span><i class="fa-solid fa-arrow-right-to-bracket"></i></span>
		</div>
	</nav>
	<div class="container">
		<div class="audioImage"></div>
		<div class="player-module">
			<img class="audioCover" id="audioCover" alt="Audio Cover" src="/images/alterImg.png">
			<div class="audio-player">
				<audio id="audio" ></audio>
				
				<input type="range" id="progress" value="0" min="0" max="100">
				<div id="time" class="time"><span id="time1">0:00</span> <span id="time2">0:00</span></div>
				<div class="player-btn-container">
					<button id="shuffle" class="player-btn shuffle-repeate"><i class="fa-solid fa-shuffle"></i></button>
					<button id="backward" class="player-btn"><i class="fa-solid fa-backward"></i></button>
					<button id="play-pause" class="player-btn pause-play"><i class="fa-solid fa-play" id="icon"></i></button>
					<button id="forward" class="player-btn"><i class="fa-solid fa-forward"></i></button>
					<button id="repeate" class="player-btn shuffle-repeate"><i class="fa-solid fa-repeat"></i></button>
				</div>
				
				<div class="volume-container">
					<i class="fa-solid fa-volume-high"></i>
					<input type="range" id="volume" min="0" max="1" step="0.1" value="1">
				</div>
			</div>
		</div>
		<div class="songs-container">
			<form id="search-audio">
				<div class="search-audio">
					<input type="text" id="song-name">
					<button type="submit" class="search-btn"><i class="fa-solid fa-magnifying-glass"></i></button>
				</div>
				<div id="songs-list" class="songs-list">
					<table class="all-songs-table">
						<thead></thead>
						<tbody id="all-songs"></tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/js/audio.js"></script>
</body>
</html>