<link rel="stylesheet" href="<@spring.url '/static/css/register.css'/>"/>
<div class="signup">
	<h1>Sign Up For free</h1>
	<div class="signupcontent">
		<fieldset>
			<label> Display Name:</label>
			<input type="text" value="" name="displayname" id="displayname"/>
		</fieldset>
		<fieldset>
			<label> Email: </label>
			<input type="email" value="" name="username" id="username"/>
		</fieldset>
		<fieldset>
			<label> Password: </label>
			<input type="password" value="" name="password" id="password"/>
		</fieldset>
		<fieldset>
			<label> Confirm Password: </label>
			<input type="password" value="" name="confirmpassword" id="confirmpassword"/>
		</fieldset>
		<fieldset>
			<button onclick="signNewUser();">Sign Up</button>
		</fieldset>
	</div>
</div>
<script type="text/javascript" src="/static/js/utils/userlogin.js"></script>
<script type="text/javascript" src="/static/js/home.js"></script>