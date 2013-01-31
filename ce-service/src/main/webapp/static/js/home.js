/**
 * Onload function
 */
$(function(){
	user = new User($(".login"));
	//we have new form for the login and sign up 
	//user.moveToCenter(); //move login box to the center
	$(this).mouseup(function(logout) {
		if($("#logout").is(":visible") && $(logout.target).parent("#logout").length > 0) {
			$('#logout').toggle('fast');
		}
	});
});

function toggleUserEdit() {
	$('#logout').toggle('fast');
}

function signup() {
	$("#content").load("static/signup.html");
}

/**
 * Function to handle the new registered user
 */
function signNewUser() {
	var pwd = $("#password").val();
	var confirmPwd = $("#confirmpassword").val();
	if(pwd != "" && confirm) {
		if(pwd != confirmPwd) {
			alert("password not match");
		}
		else {
			var data = {"username": $("#username").val(), "password":$("#password").val(), "displayName":$("#displayname").val()};
			user.createNewUser(data, function(u){
				$("#registersuccess").slideDown();
				$(".signup").hide();
			});
		}
	}
	else {
		alert("password is empty");
	}
}

/**
 * User login function
 * @deprecated user login is handled by the spring security
 */
function userLogin() {
	var userName = $("#username").val();
	var password = $("#password").val();
	user.login(userName, password, loginFail, businessPage);
}

/**
 * Fail called back function
 * @param e
 * @deprecated login is now controlled by the spring security
 */
function loginFail(er) {
	$("#content").load("static/signup.html");
}

/**
 * User successed login
 * @deprecated
 */
function businessPage() {
	user.getAllBusinesses(function(businesses){
		if(businesses.length == 0) {
			$("#content").load("static/newbusiness.html");
			setTimeout(function(){
				$("#logged_user").text(window.JSON.parse(sessionStorage.user).displayName);
			}, 100);
		}
		else {
			$("#content").load("static/business.html");
		}
	});
}