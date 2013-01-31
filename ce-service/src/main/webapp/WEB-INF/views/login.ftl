<style>
	.footer {
		display:none;
	}
	.logout{display:none;}
</style>
<link rel="stylesheet" href="<@spring.url '/static/css/admin_main.css'/>"/>
<link rel="stylesheet" href="<@spring.url '/static/css/login.css'/>"/>
<link rel="stylesheet" href="<@spring.url '/static/css/jquery-ui-1.8.21.custom.css'/>"/>
<link rel="stylesheet" href="<@spring.url '/static/css/additional_styles.css'/>"/>

<!-- Login Starts Here -->
<div id="loginContainer">
    <a href="#" id="loginButton"><span>Login</span><em></em></a>
    <div style="clear:both"></div>
    <div id="loginBox">                
        <form name='f' action='api/j_spring_security_check' method='POST' id="loginForm">
            <fieldset id="body">
                <fieldset>
                    <label for="email">User:</label>
                    <input maxLength='30' type='text' name='j_username' value=''>
                </fieldset>
                <fieldset>
                    <label for="password">Password</label>
                    <input maxLength='30' type='password' name='j_password' />
                </fieldset>
                <input type="submit" id="login" value="Sign in" />
                <label for="checkbox"><input type="checkbox" id="checkbox" />Remember me</label>
            </fieldset>
            <span><a href="#">Forgot your password?</a></span>
        </form>
    </div>
</div>
${loginfailed!}
<!-- Login Ends Here -->
<script type="text/javascript" src="<@spring.url '/static/js/jquery-ui-1.8.21.custom.min.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/static/js/login.js'/>"></script>
