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
			<audio controls>
				<source id="source">
			</audio>
		</div>
		<div class="songs-container">
			<form>
				<div>
					<input type="text" id="search">
					<button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>