<#include "header.ftl"/>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="/">Cambodia Explorer Service</a>
				<div class="nav-collapse collapse">
					<ul class="nav navbar-text pull-right">
       					<li class="dropdown">
       						<a class="dropdown-toggle" data-toggle="dropdown">Login</a>
       						<ul class="dropdown-menu dropdown-login">
       							<li>
       								<form name='f' action='/api/j_spring_security_check' method='POST' id="loginForm">
						                <div>
						                    <label for="email">User</label>
						                    <input id="email" maxLength='30' type='text' name='j_username' value=''>
						                </div>
						                <div>
						                    <label for="password">Password</label>
						                    <input id="password" maxLength='30' type='password' name='j_password' />
						                </div>
						                <div>
							                <a onclick="userSignin();" class="btn btn-primary btn-small">Sign In</a><span style="float:right;"><input type="checkbox" id="checkbox" />Remember me</span>
							            </div>
							            <div><a>Forgot your password?</a></div>
							        </form>
       							</li>
       						</ul>
       					</li>
       					<!--
       					<li class="dropdown">
	         				<a class="dropdown-toggle" data-toggle="dropdown"><span class="icon-white icon-user"></span> <span id="headerUsername"></span> <b class="caret"></b></a>
	         				<ul class="dropdown-menu">
	         					<li class="divider"></li>
		         				<li><a id="headerUserAction" href="" >Logout</a></li>
	         				</ul>
       					</li> -->
       				</ul>
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="contents-wrapper">
			<div class="alert alert-success hide">
				<a class="close" data-dismiss="alert">×</a>
				<div id="alert-message"></div>
			</div>  
			<div class="sign-up">
				<form class="form-horizontal">
					<fieldset>
						<div style="width:100%;text-align:center;"><h1> Sign Up For Free </h1></div>
						<div class="control-group">
							<label class="control-label">Display Name</label>
							<div class="controls">
								<input type="text" value="" name="displayname" id="displayname"/>
							</div>
						</div>
						<div class="control-group">  
							<label class="control-label" for="username">Email</label>  
							<div class="controls">
								<input type="text" value="" name="username" id="username">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">Password</label>  
							<div class="controls">
								<input type="password" value="" name="password" id="password">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="confirmpassword">Confirm password</label>
							<div class="controls">
								<input type="password" value="" id="confirmpassword" name="confirmpassword">
							</div>
						</div>
						 <div class="control-group">
						 	<div class="controls">
								<button type="button" class="btn btn-primary btn-big" onclick="signNewUser();" style="width:100px;">Save</button>
							</div>
						</div>
					</fieldset>
				</form>
			<div>
		</div>
	</div> <!-- /container -->
	<script type="text/javascript">
		var user = new User();
		$(function(){
			//check user logged in
			user.getUserLogin(function(u){loadUserHome(u);}, function(){
				$("#loginForm").iframePostForm({
					json : true,
					complete : function (response){
						console.log(response);
						loadUserHome(response);
					}
				});
			});
		});
		function loadUserHome(u) {
			console.log(u);
			loadPageContent("business");
		}
		function userSignin() {
			$("#loginForm").submit();
		}
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
			var pwd = $(".sign-up #password").val();
			var confirmPwd = $("#confirmpassword").val();
			if(pwd != "" && confirm) {
				if(pwd != confirmPwd) {
					alert("password not match");
				}
				else {
					var data = {
						"username": $("#username").val(), 
						"password":pwd, 
						"displayName":$("#displayname").val()
					};
					user.createNewUser(data, function(u){
						$("#alert-message").append("<strong>Thanks!</strong> You will need to login to add your business.");
						$(".contents-wrapper .alert").removeClass("hide");
					});
				}
			}
			else {
				alert("password is empty");
			}
		}
	</script>
	<!--
<#include "footer.ftl"/> -->