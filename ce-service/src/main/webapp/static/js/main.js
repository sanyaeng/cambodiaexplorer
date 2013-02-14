var defaultLocation = new Location(11.574157, 104.926771);


/**
 * Update current location failed
 * @param p
 */
function updFailed(p) {
	if(p.code == 1) {
		alert(messages.accessDeniedText);
	} else if(p.code == 2) {
		alert(messages.positionUnavailableText);
	} else if(p.code == 3) {
		alert(messages.timeoutText);
	}
	abortGeo = true;
	new GMap("newbusiness", defaultLocation, false);
}

/**
 * Update current location
 */
function updLocation(p) {
	currentLat = p.coords.latitude;
	currentLon = p.coords.longitude;
	abortGeo = true;
	var location = new Location(currentLat, currentLon);
	if(gmap == undefined) {
		gmap = new GMap("newbusiness", location, true);
		gmap.init();
	}
	else {
		gmap.refresh(location);
	}
	setTimeout(function(){
		google.maps.event.addListener(gmap.map, 'click', function(event) {
			gmap.placeMarker(event.latLng, false, 'images/flag'+trim($("#businesstype option:selected").text()).toLowerCase()+'.png', function(clickedLocation){
				$("#lat").val(clickedLocation.lat());
				$("#lon").val(clickedLocation.lng());
			});
		});
	}, 500);
}

/**
 * Remove out the space
 */
function trim(str) {
	var splitStr = str.split(" ");
	var newStr = "";
	for(var i in splitStr){
		newStr += splitStr[i];
	}
    return newStr;
}

//load the page to the container
function loadPageContent(page) {
	$.get("" + page + ".html", function(content){
		$(".contents-wrapper").html(content);
	});
}
