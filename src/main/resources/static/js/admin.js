
document.getElementById("upload-file").addEventListener("submit", (event) => {
	event.preventDefault();
	
	const mediaFile = document.getElementById('inputFile').files[0];
	const imageFile = document.getElementById('inputImage').files[0];
	const language = document.getElementById('language');
	if (!mediaFile) {
		alert("Please select a file!");
		return;
	}
	
	const formData = new FormData();
	formData.append("mediaFile", mediaFile);
	formData.append("imageFile", imageFile);
	formData.append('lang', language.value)
	
	fetch("/getAudio", {
		method: "POST",
		body: formData
	})
	.then(res => res.text())
	.then(data => {
		console.log(data)
		language.value = '';
	})
	.catch(err => alert(err.message));
});