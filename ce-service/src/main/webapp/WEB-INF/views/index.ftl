<#include "header.ftl"/>
	<script type="text/javascript" src="/static/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-ui-1.8.21.custom.min.js"></script>
	<script type="text/javascript" src="/static/js/iscroll.js"></script>
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
	<div class="wrapper" style="">
		<div class="content" id="content" style="clear:both;">
			<div id="bar">
				<div id="container">
					<#include "${bodyPage}.ftl"/>
				</div>
			</div>
			<div id="registersuccess" style="display:none;width:450px;margin:0 auto;padding-top:50px;">Thanks for registration. You need to log in to add your business.</div>
			<#include "register.ftl"/>
		</div>
	</div>
<#include "footer.ftl"/>