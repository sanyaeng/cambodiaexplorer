(function(){
	var User = function(container){
		var that = this;
		that.userUrl = "http://" + location.host + "/user/";
		that.container = container;
		that.message = "";
		that.loginUser;
	};
	User.prototype = {
		getAllBusinesses: function(callback) {
			var that = this;
			var businesses;
			$.ajax({
				url: that.userUrl + "api/getAllBusiness",
				type: "GET",
				dataType: "json",
				success: function(d) {
					callback(d);//businesses = d;
				},
				error: function(jqXHR, errString, errorThrown) {
				}
			});
		},
		/**
		 * Move the user login container to the center of the screen
		 */
		moveToCenter: function(){
			var that = this;
			var windowH = $(window).height();
			var windowW = $(window).width();
			var containerH = that.container.height();
			var containerW = that.container.width();
			var centerTop = windowH/2 - containerH/2;
			var centerLeft = windowW/2 - containerW/2;
			that.container.css({
				"top": centerTop,
				"left": centerLeft,
			});
			
		},
		/**
		 * Create the new user
		 * @param data: 
		 */
		createNewUser: function(data, callback) {
			var that = this;
			$.ajax({
				url: that.userUrl + "api/addNew",
				data: data,
				type:'POST',
				dataType: 'json',
				success: function(userLogin) {
					that.loginUser = userLogin;
					if(typeof(callback) === 'function'){
						callback();
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				statusCode: {
					409: function(){
						alert("Username is already registered!");
					}
				}
			});
		},
		getUserLogin: function(callback) {
			var that = this;
			$.ajax({
				url: that.userUrl + "api/getMe",
				type: "GET",
				dataType: "json",
				success: function(userLogin) {
					callback(userLogin);
				},
				error: function(jqXHR, status, errorThrown) {
					switch(status) {
						case 401:
							break;
						default:
					}
				},
			});
		},
		/**
		 * Login user will send the ajax request to the conctroller
		 * @param username: the user name for login
		 * @param password: the password
		 * @param fail: callback function when the login fail
		 * @param success: callback function when login success
		 * @deprecated: using the spring security instead
		 */
		login: function(username, password, fail, successCallback) {
			var that = this;
			if(username == "" || password == "") {
				var message = "User name or password cannot be empty";
				if(typeof(fail) === 'function') {
					fail(message);
				}
				else {
				}
			}
			else {
				$.ajax({
					url: that.userUrl + 'userlogin',
					dataType: 'json',
					type: 'POST',
					data: {
						username:username,
						password:password,
					},
					success:function(userLogin){
						that.loginUser = userLogin;
						sessionStorage.setItem("user", that.loginUser);
						alert("ss");
						successCallback();
					},
					error: function(jqXHR, textStatus, errorThrown) {
						switch(jqXHR.status) {
						case 401:
							fail("User name or password not match");
							break;
						default:
							fail("User not found");
							break;
						}
					}
				});
			}
		}
	};
	if (typeof exports !== 'undefined') exports.User = User;
	else window.User = User;
})();