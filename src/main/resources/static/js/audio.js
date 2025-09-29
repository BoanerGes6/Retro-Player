const tableBody = document.getElementById("all-songs")
const slider = document.getElementById("progress");
const volumeSlider = document.getElementById('volume');
const audio = document.getElementById('audio');
const playPauseBtn = document.getElementById('play-pause');
const forwardBtn = document.getElementById('forward');
const backwardBtn = document.getElementById('backward');
const timeDisplay1 = document.getElementById('time1');
const timeDisplay2 = document.getElementById('time2');
const icon = document.getElementById('icon');
const audioCover = document.getElementById('audioCover');

const page = 0;
const size = 10;

window.addEventListener("DOMContentLoaded", () => {
	fetch(`/allSongs?page=${page}&size=${size}&sort=id,desc`)
	.then(response => response.json())
	.then(data => {
		const songs = data.content;
		if (songs.length === 0) {
			tableBody.innerHTML = `<tr>
			<td colspan="6" style="text-align:center;">NO SONGS FOUND</td>
			</tr>`;
		} else {
			tableRender(songs);
		}
	});
});

document.getElementById("search-audio").addEventListener("submit", (event) => {
	event.preventDefault();
	
	const songName = document.getElementById('song-name');
	const query = songName.value.trim();
	
	if (query === "") return;
	
	fetch(`/audioSearch?query=${encodeURIComponent(query)}`)
	.then(response => response.json())
	.then(async () => {
		const response = await fetch("/allSongs");
		return await response.json();
	})
	.then(data => {
		console.log(data);
		if (data.length === 0) {
			tableBody.innerHTML = `<tr>
			<td colspan="6" style="text-align:center;">NO SONGS FOUND</td>
			</tr>`;
		} else {
			tableRender(data);
		}
	})
	.catch(error => alert(error.message()));
});

function tableRender(data) {
	tableBody.innerHTML = '';
	data.forEach(song => {
		const row = document.createElement("tr");

		row.innerHTML = `
			<td>
				<button type="button" class="song-btn" data-id="${song.songName}">
				<div style="background-image: url('${song.imagePath}${song.imageName}')"></div>
				<div>${song.songName}</div></button>
			</td>
		`;
		tableBody.appendChild(row);
	});
}

let selectedAudio = null;
let currentSongName = null;
tableBody.addEventListener('click', function(event) {
	
	if (event.target.classList.contains('song-btn')) {
		if (selectedAudio) {
			selectedAudio.classList.remove('song-btnV2');
		}
		event.target.classList.add('song-btnV2');
		
		selectedAudio = event.target;
		selectedName = selectedAudio.getAttribute('data-id');
		currentSongName = selectedName;
		audio.pause();
		fetch(`/selectedSongInfo?query=${encodeURIComponent(selectedName)}`)
		.then(response => response.blob())
		.then(blob => {
			console.log(blob);
			audioCover.src = URL.createObjectURL(blob);
		})
		.catch(err => alert(err.message));
		
		fetch("/selectedSong?query=" + encodeURIComponent(selectedName))
		.then(response => response.blob())
		.then(blob => {
			audio.src = URL.createObjectURL(blob);
			audio.play();
			console.log("Duration this time : ", audio.duration);
		})
		
		icon.classList.remove('fa-play');
		icon.classList.add('fa-pause');
		icon.classList.add('fa-pauseMargin');

	}
	
	console.log(selectedAudio.getAttribute('data-id'));
	
});

// forward and backward
forwardBtn.addEventListener('click', () => {
	if (!isNaN(audio.duration)) {
		audio.currentTime = Math.min(audio.currentTime + 30.0, audio.duration);
	}
});
backwardBtn.addEventListener('click', () => {
	if (!isNaN(audio.duration)) {
		console.log('clicked');
		audio.currentTime = Math.max(audio.currentTime - 30.0, 0);
	}
})

// pause and play button
playPauseBtn.addEventListener('click', () => {
	if(audio.paused) {
		audio.play();
		icon.classList.remove('fa-play');
		icon.classList.add('fa-pause');
		icon.classList.add('fa-pauseMargin');
	} else {
		audio.pause();
		icon.classList.remove('fa-pause');
		icon.classList.remove('fa-pauseMargin');
		icon.classList.add('fa-play');
	}
});


// update progress Bar slider
function updateProgressSlider() {
	
	slider.max = audio.duration;
	slider.value = audio.currentTime;
	
	const minutes = Math.floor(audio.currentTime / 60);
	const seconds = Math.floor(audio.currentTime % 60).toString().padStart(2, '0');
	timeDisplay1.textContent = `${minutes}:${seconds}`;
	
	const totalMinutes = Math.floor(audio.duration / 60);
	const totalSeconds = Math.floor(audio.duration % 60).toString().padStart(2, '0');
	if (audio.duration)
		timeDisplay2.textContent = `${totalMinutes}:${totalSeconds}`;
	
	const percentage = (audio.currentTime / audio.duration) * 100;
	
	slider.style.background = `linear-gradient(to right, #ff5722 0%, #ff5722 ${percentage}%, #ccc ${percentage}%, #ccc 100%)`;
}
function autoplayNextSong() {
	if (audio.currentTime === audio.duration) {
		/* slider.value = 0;
		slider.style.background = `none`; */
		fetch("/autoplayNextSong?query=" + encodeURIComponent(currentSongName))
		.then(response => response.blob())
		.then(blob => console.log(blob))
		.catch(err => alert(err.message));
	}
		
}
audio.addEventListener('timeupdate', () => {
	updateProgressSlider();
	autoplayNextSong();
});

slider.addEventListener('input', () => {
	audio.currentTime = slider.value;
  updateProgressSlider();
});

// update Volume
function updateVolumeBackground() {
	const value = parseFloat(volumeSlider.value);
	const max = parseFloat(volumeSlider.max);
	const percentage = (value / max) * 100;
	
	volumeSlider.style.background = `linear-gradient(to right, #ff5722 0%, #ff5722 ${percentage}%, #ccc ${percentage}%, #ccc 100%)`;
	audio.volume = value;
}

// Update dynamically
volumeSlider.addEventListener('input',updateVolumeBackground);
updateVolumeBackground();


