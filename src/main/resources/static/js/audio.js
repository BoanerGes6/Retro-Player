const tableBody = document.getElementById("all-songs")
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

tableBody.addEventListener('click', function(event) {
	if (event.target.classList.contains('song-btn')) {
		if (selectedAudio) {
			selectedAudio.classList.remove('song-btnV2');
		}
		event.target.classList.add('song-btnV2');
		
		selectedAudio = event.target;
		selectedName = selectedAudio.getAttribute('data-id');
		
		fetch(`/selectedSongInfo?query=${encodeURIComponent(selectedName)}`)
		.then(response => response.json())
		.then(data => {
			console.log(data);
		})
		.catch(err => alert(err.message));
		
		audio.src = "/selectedSong?query=" + encodeURIComponent(selectedName);
		audio.play();
	}

	console.log(selectedAudio.getAttribute('data-id'));
});
