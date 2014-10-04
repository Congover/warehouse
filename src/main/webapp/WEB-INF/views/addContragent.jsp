<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item menu_selected'><a href='${pageContext.request.contextPath}/contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item '><a href='${pageContext.request.contextPath}/dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
				<form class="form-signin" name='f' action="save" method='POST' id="formContragent">
					<p class="fieldrow">
						<label class="fieldlabel">Наименование</label>
						<input class="fieldfld" type="text" required name="value" value="" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Адрес</label>
						<input class="fieldfld" type="text" name="address0" value="" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input class="fieldfld" type="text" name="address1" value="" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input class="fieldfld" type="text" name="address2" value="" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input class="fieldfld" type="text" name="address3" value="" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input class="fieldfld" type="text" name="address4" value="" />
					</p>
					<div class="buttons">
						<div class="button">
							<input style="display:none;" id="submitBtn" type="submit"/>
							<a href="javascript:{}" onclick="document.getElementById('submitBtn').click();"><spring:message code="btn.save"/></a>
						</div>
						<div class="button">
							<a onclick='history.back()'><spring:message code="btn.back"/></a>
						</div>
					</div>						
				</form>
		</div>
	</div>
</body>
</html>