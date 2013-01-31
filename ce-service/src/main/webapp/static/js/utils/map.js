/**
 * Constructor. 
 * @param mapType can be listing or new business
 * @param location Location
 * @returns
 */
(function(){
	var GMap = function(mapType, location, isCurrentLocation){
		//all the private methods
		var that = this;
		that.marker = null;
		that.currentLocMarker = null;
		that.mapType = mapType;
		that.location =location;
		that.map = null;
		that.isCurrentLocation = isCurrentLocation;
		that.markers = new Collection();
		that.bubbles = new Collection();
		/**
		 * Clear existing marker
		 */
		that._clearMarker = function() {
			var that = this;
			if(that.marker != null) {
				that.marker.setMap(null);
			}
		};
		that._clearAllMarkers = function() {
			var that = this;
			var keys = that.markers.getKeys();
			console.log(" >>> : " + that.markers);
			for(var k in keys) {
				that.markers.getItem(keys[k]).setMap(null);
			}
			that.markers.removeAll();
		};
		/**
		 * clear existing current location marker
		 */
		that._clearCurrentLocationMarker = function() {
			var that = this;
			if(that.currentLocMarker != null) {
				that.currentLocMarker.setMap(null);
			}
		}
		/**
		 * set current location circle
		 */
		that._setCurrentLocationCircle = function(currentLoc) {
			var populationOptions = {
					strokeColor: "#6a96d8",
					strokeOpacity: 0.8,
					strokeWeight: 2,
					fillColor: "#6a96d8",
					fillOpacity: 0.35,
					map: that.map,
					center: currentLoc,
					radius: 300
			};
			cityCircle = new google.maps.Circle(populationOptions);
		};
		/**
		 * attach info to the given marker
		 */
		that._attachInfor = function(marker, messageObj, selectItem) {
			that.markers.add(selectItem, marker);
			var infoWindow = new InfoBubble({
				map: that.map,
				content: messageObj,
		          /*position:bubbleLoc,*/
		          shadowStyle: 1,
		          padding: 0,
		          backgroundColor: 'rgb(57,57,57)',
		          borderRadius: 4,
		          arrowSize: 10,
		          borderWidth: 1,
		          borderColor: '#2c2c2c',
		          disableAutoPan: true,
		          hideCloseButton: true,
		          arrowPosition: 30,
		          backgroundClassName: 'phoney',
		          arrowStyle: 2,
		          maxHeight: 60,
		          minWidth: 150
			});
			that.bubbles.add(selectItem, infoWindow);
			google.maps.event.addListener(marker, 'click', function() {
				//clear all open bubble
				var keys = that.bubbles.getKeys();
				for(i in keys) {
					console.log(that.bubbles.getItem(keys[i]));
					if(that.bubbles.getItem(keys[i]).isOpen()) {
						that.bubbles.getItem(keys[i]).close();
					}
				}
				//open the clicked one
				infoWindow.open(that.map, marker);
			});
			console.log("bubble count: " + that.bubbles.count());
		}
	};
	
	//
	GMap.prototype = {
		setMap: function(map) {
			var that = this;
			that.map = map;
		},
		getMap: function() {
			var that = this;
			return that.map;
		},
		setMapType: function(mapType) {
			var that = this;
			that.mapType = mapType;
		},
		/**
		 * all the bubble of the related marker will be return within the callback
		 */
		placeAllMarkers: function(latLonList, callback) {
			var that = this;
			that._clearMarker();
			that.mapType = "listing";
			var bounds = new google.maps.LatLngBounds();
			for(var i in latLonList) {
				var gLatLon = new google.maps.LatLng(latLonList[i].getLatitude(), latLonList[i].getLongitude());
				this.placeMarker(gLatLon, false, latLonList[i].getLocationIcon());
				that._attachInfor(that.marker, latLonList[i].getLocationInfo(), latLonList[i].getItemIndex());
				bounds.extend(gLatLon);
			}
			//make the map to fit the boundries
			that.map.fitBounds(bounds);
			//that.map.setCenter();
			if(typeof callback == 'function') {
				callback(that.bubbles);
			}
		},
		/**
		 * Show the bubble of the given index
		 */
		showBubble: function(i) {
			var that = this;
			var bubble = that.bubbles.getItem(i);
			var marker = that.markers.getItem(i);
			this.hideBubble();
			bubble.open(that.map, marker);
		},
		/**
		 * place the marker depends on the user clicked
		 */
		placeMarker: function(clickedLocation, isUserLocation, icon, callback) {
			var that = this;
			this.hideBubble();
			if(isUserLocation) {
				that.currentLocMarker = new google.maps.Marker({
					position: clickedLocation,
					map: that.map,
					icon: "/static/images/userPin.png",
					title: "Your current location"
				}); 
			}
			else {
				if(that.mapType != "listing") {
					that._clearMarker();
					that.map.setCenter(clickedLocation);
				}
				that.marker = new google.maps.Marker({
					position: clickedLocation,
					map: that.map,
					animation: google.maps.Animation.DROP,
					icon: icon,
				});
			}
			
			if(typeof callback == "function") {
				callback(clickedLocation);
			}
		},
		hideBubble: function() {
			var that = this;
			var keys = that.bubbles.getKeys();
			for(i in keys) {
				console.log(that.bubbles.getItem(keys[i]));
				if(that.bubbles.getItem(keys[i]).isOpen()) {
					that.bubbles.getItem(keys[i]).close();
				}
			}
		},
		
		/**
		 * Init the map
		 */
		init: function(){
			console.log("init...");
			var that = this;
			var myLatLng = new google.maps.LatLng(that.location.getLatitude(),that.location.getLongitude());
			var myOptions = {
				zoom: 15,
				center: myLatLng,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			}

			that.map = new google.maps.Map(document.getElementById("mapcanvas"), myOptions);
			if(that.mapType != "listing") {
				if(that.isCurrentLocation) {
					this.placeMarker(new google.maps.LatLng(that.location.getLatitude(), that.location.getLongitude()), true);
					//setCurrentLocationCircle(myLatLng);
				}
			}
		},
		refresh: function(currentLocation) {
			var that = this;
			this.hideBubble();
			//clear bubbles
			that.bubbles.removeAll();
			//clear markers
			that._clearAllMarkers();
			var newOption = {
				center: new google.maps.LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
				zoom: 15,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			}
			that.map.setOptions(newOption);
			that.currentLocMarker = new google.maps.Marker({
				position: new google.maps.LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
				map: that.map,
				icon: "/static/images/userPin.png",
				title: "Your current location"
			}); 
		}
	};
	if (typeof exports !== 'undefined') exports.GMap = GMap;
	else window.GMap = GMap;

})();

