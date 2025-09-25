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
		<div class="profile">
			<span><i class="fa-solid fa-user-tie"></i></span>
			<span><i class="fa-solid fa-arrow-right-to-bracket"></i></span>
		</div>
	</nav>
	<div class="container">
		<div class="audioImage"></div>
		<div class="player-module">
			<div class="audioCover"></div>
			<div class="audio-player">
				<audio id="audio"></audio>
				
				<button id="backward"></button>
				<button id="play-pause"></button>
				<button id="forward"></button>
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