
document.getElementById("login").addEventListener("submit", (event) => {
	event.preventDefault();
	
	const email = document.getElementById("email");
	const password = document.getElementById("password");
	
	const formData = new FormData();
	formData.append("email", email.value);
	formData.append("password", password.value);
	
	fetch("/login", {
		method: "POST",
		body: formData
	})
	.then(response => response.json())
	.then(data => {
		console.log(data);	
		if (data.success === true) {
			console.log("Login Success");
			window.location.href = `/AudioPlayer/${data.id}`;
			
		}else {
			console.log("Invalid.");
		}
		email.value ='';
		password.value ='';
	})
	.catch(err => alert(err.message))
});
