/**
 * src: http://www.html5rocks.com/en/tutorials/file/dndfiles/
 * @param evt
 */
function handleFileSelect(evt) {
	var files = evt.target.files,
		container = document.getElementById("photo-thumbnail-container"); // FileList object
	//
	container.style.width = (100 * files.length) + (20 * files.length) + "px";
	// Loop through the FileList and render image files as thumbnails.
	for (var i = 0, f; f = files[i]; i++) {
		// Only process image files.
		if (!f.type.match('image.*')) {
			continue;
		}
		
		var reader = new FileReader();
		
		// Closure to capture the file information.
		reader.onload = (function(theFile) {
			return function(e) {
				// Render thumbnail.
				var thumbnail = document.createElement('span'),
					image = document.createElement("img");
				image.src = e.target.result;
				image.setAttribute("title", escape(theFile.name));
				thumbnail.setAttribute("class", "photo-upload-thumnail");
				thumbnail.appendChild(image);
				
				container.appendChild(thumbnail);//document.getElementById('list').insertBefore(span, null);
			};
		})(f);
		
		// Read in the image file as a data URL.
		reader.readAsDataURL(f);
	}
}