<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- BEGIN Header --> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head class="mardaoHtml">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/mardao.css" />
    <title><c:out value="${param.headTitle}" /></title>
</head>
<body class="mardaoBody">
<!-- bread crumbs go here if any: -->
<c:if test="${null != param.headBreadcrumbText}">
<div class="mardaoBreadcrumbs">
	<c:if test="${null != param.headBreadcrumbHome}">
    <a href="<c:out value='${param.headBreadcrumbHref}'/>" class="mardaoA">
		<c:out value="${param.headBreadcrumbHome}" />
	</a>
	</c:if>
<%
    final String[] paths = (String[]) request.getAttribute("paths");
	
	// build links
	final StringBuffer href = new StringBuffer("/");
	for (int i = 1; i < paths.length-1; i++) {
		href.append(paths[i]);
		href.append("/");
%>
/ <a href="<%= href.toString() %>" class="mardaoA"><%= paths[i] %></a><%
	}
%>
/ <c:out value="${param.headBreadcrumbText}" />
</div>
</c:if>
<!-- END Header --> 	