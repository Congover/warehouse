<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item'><a href='contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item menu_selected'><a href='report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item'><a href='dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
</body>
</html>