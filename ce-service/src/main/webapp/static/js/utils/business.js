(function(){
	var Business = function() {
		var that = this;
		that.businessUrl = "http://" + location.host + "/business/";
		that.businessDetailUrl = "http://" + location.host + "/api/businessDetail";
	};
	Business.prototype = {
		getAllBusinessCategories: function(callback){
			var that = this;
			var categories;
			$.ajax({
				url: that.businessUrl + "allCategories",
				type: "GET",
				dataType: "json",
				success: function(businessCats) {
					callback(businessCats);
				},
				error: function(jqXHR, textStatus, errorThrown){
					console.log("can't get business category: " + jqXHR.status + " : " + errorThrown);
				}
			});
		},
		addNew: function(businessData, callback) {
			var that = this;
			$.ajax({
				url: that.businessUrl + "api/addNewBusiness",
				type: "POST",
				dataType: "json",
				data: businessData,
				success: function() {
					callback();
					//alert("data saved!!!");
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert("Internal server error: " + jqXHR.status + " : " + errorThrown);
				}
			});
		},
		getAllBusiness: function(callback) {
			var that = this;
			$.ajax({
				url: that.businessUrl + "api/getAllUserBusiness",
				type: "GET",
				dataType: "json",
				success: function(listing) {
					callback(listing);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Internal server error: " + jqXHR.status + " : " + errorThrown);
				}
			})
		},
		getBusiness: function(id, callback) {
			var that = this;
			$.ajax({
				url: that.businessUrl + "api/getUserBusiness",
				type: "GET",
				dataType: "json",
				data: {"id": id},
				success: function(item) {
					if(typeof callback === 'function') {
						callback(item);
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Internal server error: " + jqXHR.status + " : " + errorThrown);
				}
			});
		},
		getDetail: function(id, callBack) {
			var that = this;
			$.ajax({
				url: that.businessDetailUrl + "/detail?id="+id,
				type: "GET",
				dataType: "json",
				success: function(detail){
					if(typeof callBack === 'function') {
						callBack(detail);
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Internal server error: " + jqXHR.status + " : " + errorThrown);
				}
			});
		},
	};
	if (typeof exports !== 'undefined') exports.Business = Business;
	else window.Business = Business;
})();