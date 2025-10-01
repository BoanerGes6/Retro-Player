const closeOpt = document.getElementById('closeLogSignOpt');

document.getElementById('audioPlayerLink').addEventListener('click', function(event) {
	event.preventDefault();
	
	fetch("/isLoggedIn", {
		method: "GET",
		credentials: "include"
	})
	.then(response => response.json())
	.then(data => {
		if (data.loggedIn) {
			window.location.href= `/AudioPlayer/${data.userId}`;
		} else {
			console.log("Not created")
			closeOpt.style.display="flex";
		}
	})
})

document.getElementById('logOut').addEventListener('click', () => {
	
	fetch("/logOut", {
		method: "POST",
		credentials: "include"
	})
	.then(response => response.text())
	.then(data => {
		console.log(data);
		window.location.href="/index";
	})
	.catch(err => alert(err.message));
})

closeOpt.addEventListener('click', () => {
	closeOpt.style.display = "none";
})

document.querySelector('.logContainer').addEventListener('click', (event) => {
	event.stopPropagation();
})
