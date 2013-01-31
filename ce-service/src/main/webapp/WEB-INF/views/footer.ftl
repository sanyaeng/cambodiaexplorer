	</div> <#-- end of the content wrapper-->
	<div class="logout" id="logout">
		<div class="logoutwrapper">
			<div class="userbutton">
				<a href="<@spring.url '/j_spring_security_logout' />" id="logoutButton">Log Out</a>
			</div>
			<div class="userbutton edituser">
				<a href="">Edit User</a>
			</div>
		</div>
	</div>
	<div class="bar footer">
		<#assign user=""/>
		<#if Session.USER_KEY??>
			<#assign user = Session.USER_KEY.email/>
		</#if>
		<div class="user_footer">
			<a id="logged_user" onclick="toggleUserEdit();"></a>
		</div>
	</div>
</body>
</html>