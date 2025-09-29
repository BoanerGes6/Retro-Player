
document.getElementById("newSignin").addEventListener("submit", (event) => {
	event.preventDefault();
	
	const email = document.getElementById("email");
	const userName = document.getElementById("userName");
	const password = document.getElementById("password");
	
	console.log(email.value);
	fetch("/signup", {
		method: "POST",
		headers: {
			"content-type" : "application/json"
		},
		body: JSON.stringify({
			email : email.value,
			userName : userName.value,
			password : password.value
		})
	})
	.then(response => response.text())
	.then(data => console.log(data))
	.catch(err => alert(err.message))
});