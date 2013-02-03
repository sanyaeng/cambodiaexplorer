(function(){
	(function(){
		var BusinessDetail = function() {
			var that = this;
			that.detailUrl = "http://" + location.host + "/api/businessDetail";
		};
		BusinessDetail.prototype = {
			getDetail: function(businessId, success, fail){
				var that = this;
				$.ajax({
					url: that.detailUrl + "/detail?businessId="+businessId,
					type: "GET",
					dataType: "json",
					success: function(detail){
						if(typeof callBack === 'function') {
							success(detail);
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						if(typeof fail === 'function') {
							fail(jqXHR.status);
						}
					}
				});
			},
			addDetail: function(detail, success, fail) {
				var that = this;
				
			},
		};
		if(typeof exports != "undefined") exports.BusinessDetail = BusinessDetail;
		else window.BusinessDetail = BusinessDetail;
	})();
	var Business = function() {
		var that = this;
		that.businessUrl = "http://" + location.host + "/business/";
		//that.businessDetailUrl = "http://" + location.host + "/api/businessDetail";
		that.detail = new BusinessDetail();
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
		getDetail: function(detail, callBack, failCallback) {
			var that = this;
			that.detail.getDetail(detail, callBack, failCallback);
		},
	};
	if (typeof exports !== 'undefined') exports.Business = Business;
	else window.Business = Business;
})();