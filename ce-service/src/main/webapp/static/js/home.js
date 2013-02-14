/**
 * Onload function
 */

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
