<#include "header.ftl"/>
	<div class="wrapper" style="">
		<div id="bar">
			<div id="container">
				<#assign admin = false>
				<#if Session.USER_ROLE??>
					<#list Session.USER_ROLE as role>
						<#if role == 'ROLE_ADMIN'>
							<#assign admin = true>
						</#if>
					</#list>
					<#if admin??>
						<div class="menu">
							<div class="menu_wrapper">
								<div id="category" style="float:left;"><a href="<@spring.url '/admin/businesscategory.html'/>">Business Categories</a></div><img class="verticalseparator" src="<@spring.url '/static/images/vertical_separator.png'/>"/>
								<div id="comoption" style="float:left;"><a href="<@spring.url '/admin/communication.html'/>">Communication Option</a></div><img class="verticalseparator" src="<@spring.url '/static/images/vertical_separator.png'/>"/>
								<div id="users" style="float:left;"><a href="<@spring.url '/admin/users.html'/>">Users</a></div><img class="verticalseparator" src="<@spring.url '/static/images/vertical_separator.png'/>"/>
							</div>
						</div>
						<a href="<@spring.url '/j_spring_security_logout' />" id="logoutButton"><span>Logout</span><em></em></a>
					</#if>
				</#if>
				<#if !admin>
					<#include "*/${bodyPage}.ftl"/>
				</#if>
			</div>
		</div>
		<#if admin>
			<div id="body_container">
				<#include "*/${bodyPage}.ftl"/>
			</div>
		</#if>
	</div>
<#include "*/footer.ftl"/>