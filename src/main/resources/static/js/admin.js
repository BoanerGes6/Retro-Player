
document.getElementById("upload-file").addEventListener("submit", (event) => {
	event.preventDefault();
	
	const mediaFile = document.getElementById('inputFile').files[0];
	const imageFile = document.getElementById('inputImage').files[0];
	if (!mediaFile) {
		alert("Please select a file!");
		return;
	}
	
	const formData = new FormData();
	formData.append("mediaFile", mediaFile);
	formData.append("imageFile", imageFile);
	
	fetch("/getAudio", {
		method: "POST",
		body: formData
	})
	.then(res => res.text())
	.then(data => {
		console.log(data)
	})
	.catch(err => alert(err.message));
});