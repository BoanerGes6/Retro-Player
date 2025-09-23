<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Player</title>
<link rel="stylesheet" href="/css/index.css">
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
		<div>
			<img usemap="#audioVideoMap" alt="No image found" src="/images/audio video.png">
			<map id="audioVideoMap">
				<area shape="rect" coords="0, 0, 250, 600" href="/AudioPlayer">
				<area shape="rect" coords="250, 0, 500, 600" href="www.yahoo.com">
			</map>
		</div>
		<div class="about-us">
			<fieldset>
				<legend>🎵 Experience Music Like Never Before – Zero Ads, 100% Free 🎵</legend>
				<p>Tired of interruptions while enjoying your favorite tunes? 
				Discover the ultimate music player that gives you seamless, 
				ad-free listening without spending a dime. 
				Whether you're at home, on a commute, or working out, 
				immerse yourself in crystal-clear sound without the distraction of annoying commercials.</p>
			</fieldset>
		</div>
	</div>
	<footer class="footer">
		<div class="why">
			<h3>Why Choose Our Music Player?</h3>
			<ul>
				<li><strong>Zero Ads, Zero Interruptions:</strong> Focus entirely on your music without interruptions from pop-ups or banners.</li>
				<li><strong>Free Subscription:</strong> Access millions of songs and playlists without paying a single rupee.</li>
				<li><strong>Personalized Playlists:</strong> Let the app learn your taste and curate playlists that match your mood.</li>
				<li><strong>High-Quality Audio:</strong> Experience rich, crisp sound for every genre.</li>
				<li><strong>User-Friendly Interface:</strong> Navigate effortlessly with a sleek, modern design.</li>
			</ul>
		</div>
		<div class="conduct-us">
			<h3>Contact-us:</h3>
			<div>
				<p><i class="fa-solid fa-mobile-screen"></i><strong> Mobile:</strong> +91 88855 25377</p>
				<p><i class="fa-regular fa-envelope"></i> <strong>E-Mail:</strong> info@retroplayer.com</p>
				<p><i class="fa-regular fa-copyright"></i> 2025 Retro Player</p>
			</div>
		</div>
	</footer>
</body>
</html>