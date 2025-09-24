const tableBody = document.getElementById("all-songs")

window.addEventListener("DOMContentLoaded", () => {
	fetch("/allSongs")
	.then(response => response.json())
	.then(data => {
		if (data.length === 0) {
			tableBody.innerHTML = `<tr>
			<td colspan="6" style="text-align:center;">NO SONGS FOUND</td>
			</tr>`;
		} else {
			tableRender(data);
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
			<td><button type="button id="${song.songName}"><div>${song.image}</div><div>${song.songName}</div></button></td>
		`;
		tableBody.appendChild(row);
	});
}
