<?xml version="1.0" encoding="utf-8"?>
<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<#setting number_format="0">
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv='content-type' content='text/html; charset=utf-8' />
	<meta name="viewport" content="initial-scale = 1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<meta name="format-detection" content="telephone=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<title>Admin page</title>
	<link rel="stylesheet" href="<@spring.url '/static/css/admin_main.css'/>"/>
	<link rel="stylesheet" href="<@spring.url '/static/css/login.css'/>"/>
	<link rel="stylesheet" href="<@spring.url '/static/css/jquery-ui-1.8.21.custom.css'/>"/>
	<link rel="stylesheet" href="<@spring.url '/static/css/additional_styles.css'/>"/>
	
	<script type="text/javascript" src="<@spring.url '/static/js/jquery-1.7.2.min.js'/>"></script>
	<script type="text/javascript" src="<@spring.url '/static/js/jquery-ui-1.8.21.custom.min.js'/>"></script>
	<script type="text/javascript" src="<@spring.url '/static/js/login.js'/>"></script>
	<script type="text/javascript" src="<@spring.url '/static/js/adminpage.js'/>"></script>
</head>
<body>