(function(){
	(function(){
		var BusinessDetail = function() {
			var that = this;
			that.detailUrl = "http://" + location.host + "/businessDetail/api/v10";
		};
		BusinessDetail.prototype = {
			getDetail: function(businessId, successCallback, failCallback){
				var that = this;
				$.get(that.detailUrl + "/detail?businessId="+businessId, function(data, textStatus, jqXHR){
					if(typeof successCallback === 'function') {
						successCallback(data);
					}
				}).fail(function(){
					if(typeof failCallback === 'function') {
						failCallback();
					}
				});
				/*
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
				});*/
			},
			addDetail: function(detail, success, fail) {
				var that = this;
				$.post(that.detailUrl + "/add", detail, function(data, textStatus, jqXHR){
					if(typeof success === 'function') {
						success(data);
					}
				}).fail(function(){
					alert("Adding detail fail!!!");
				});
				
			},
		};
		if(typeof exports != "undefined") exports.BusinessDetail = BusinessDetail;
		else window.BusinessDetail = BusinessDetail;
	})();
	var Business = function() {
		var that = this;
		that.businessUrl = "http://" + location.host + "/business/api/v10";
		that.detail = new BusinessDetail();
	};
	Business.prototype = {
		getAllBusinessCategories: function(callback){
			var that = this;
			var categories;
			$.ajax({
				url: that.businessUrl + "/allCategories",
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
				url: that.businessUrl + "/addNewBusiness",
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
				url: that.businessUrl + "/getAllUserBusiness",
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
				url: that.businessUrl + "/getUserBusiness",
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
		//Business Detail Action
		getDetail: function(detail, callBack, failCallback) {
			var that = this;
			that.detail.getDetail(detail, callBack, failCallback);
		},
		addDetail: function(detail, callBack) {
			var that = this;
			that.detail.addDetail(detail, callBack);
		},
	};
	if (typeof exports !== 'undefined') exports.Business = Business;
	else window.Business = Business;
})();