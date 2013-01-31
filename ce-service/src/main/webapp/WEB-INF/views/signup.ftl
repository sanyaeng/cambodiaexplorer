<style>
	.footer {
		display:none;
	}
</style>
<form id="signupform" action="<@spring.url '/user/signup.html'/>" method="post">
	<div class="login" style="">
		<div class="loginbox" style="">
			<div class="label">
				E-mail: 
			</div>
			<div id="usernamebox" style="padding-bottom:10px;float:left;margin-left:-110px;">
				<input type="text" value="" name="username" id="username">
			</div>
			<div class="label">
				Password: 
			</div>
			<div id="passwordbox" style="padding-bottom:10px;float:left;margin-left:-110px;">
				<input type="password" value="" id="password" name="password">
			</div>
			<div class="label">
				Confirm password:
			</div>
			<div id="passwordbox" style="float:left;margin-left:-110px;">
				<input type="password" value="" id="confirmpassword" name="confirmpassword">
			</div>
			<div style="clear:left;width:394px;">
				<div style="float:left;margin-top:10px;">
					<input type="button" value="Save" id="signup" onclick="signNewUser();" style="width:63px;height:26px;margin-left:32px;"/>
				</div>
			</div>
		</div>
	</div>
</form>