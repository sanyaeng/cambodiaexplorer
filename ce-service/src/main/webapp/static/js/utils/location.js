(function(){
	//constructor
	var Location = function(lat, lon){
		//private variable
		var that = this;
		that.lat = lat;
		that.lon = lon;
		that.locationType;//used for icon on map
		that.locationInfo;
		that.itemIndex;
	};
	Location.prototype = {
		setItemIndex: function(index) {
			var that = this;
			that.itemIndex = index;
		},
		getItemIndex: function() {
			var that = this;
			return that.itemIndex;
		},
		setLocationTye: function(type) {
			var that = this;
			that.locationType = type;
		},
		getLocationType: function() {
			var that = this;
			return that.locationType;
		},
		getLatitude: function() {
			var that = this;
			return that.lat;
		},
		getLongitude: function() {
			var that = this;
			return that.lon;
		},
		getLocationIcon: function() {
			var that = this;
			return "images/flag" + that.locationType.toLowerCase() + ".png";
		},
		getLocationInfo: function() {
			var that = this;
			return that.locationInfo;
		},
		setLocationInfo: function(message) {
			var that = this;
			that.locationInfo = message;
		},
		/**
		 * Function to get the distance between 2 location
		 * @param departure Location
		 * @param destination Location
		 * @returns d: distance in Km
		 */
		getDistance: function(departure, destination) {
			var R = 6371; // Radius of the earth in km
			var dLat = (destination.getLatitude() - departure.getLatitude()).toRad();  // Javascript functions in radians
			var dLon = (destination.getLongitude() - departure.getLongitude()).toRad(); 
			var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			        Math.cos(lat1.toRad()) * Math.cos(lat2.toRad()) * 
			        Math.sin(dLon/2) * Math.sin(dLon/2); 
			var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
			var d = R * c;
			return d;
		}
	};
	if (typeof exports !== 'undefined') exports.Location = Location;
	else window.Location = Location;
})();