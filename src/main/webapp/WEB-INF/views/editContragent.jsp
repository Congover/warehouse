<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				<form class="form-signin" name='f' action="../update" method='POST' id="formContragent">
				<input type="hidden" name="id" value="${contragent.contragentId}"/>
					<p class="fieldrow">
						<label class="fieldlabel">Наименование</label>
						<input class="fieldfld" type="text" required name="value" value="${contragent.name}" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Адрес</label>
						<input type="hidden" name="address0Id" value="<c:if test="${!empty address0}">${address0.addressId}</c:if>"/>
						<input class="fieldfld" type="text" name="address0" value="<c:if test="${!empty address0}">${address0.fullAddress}</c:if>" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input type="hidden" name="address1Id" value="<c:if test="${!empty address1}">${address1.addressId}</c:if>"/>
						<input class="fieldfld" type="text" name="address1" value="<c:if test="${!empty address1}">${address1.fullAddress}</c:if>" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input type="hidden" name="address2Id" value="<c:if test="${!empty address2}">${address2.addressId}</c:if>"/>
						<input class="fieldfld" type="text" name="address2" value="<c:if test="${!empty address2}">${address2.fullAddress}</c:if>" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input type="hidden" name="address3Id" value="<c:if test="${!empty address3}">${address3.addressId}</c:if>"/>
						<input class="fieldfld" type="text" name="address3" value="<c:if test="${!empty address3}">${address3.fullAddress}</c:if>" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<input type="hidden" name="address4Id" value="<c:if test="${!empty address4}">${address4.addressId}</c:if>"/>
						<input class="fieldfld" type="text" name="address4" value="<c:if test="${!empty address4}">${address4.fullAddress}</c:if>" />
					</p>
					<div class="buttons">
						<div class="button">
							<a href="javascript:{}" onclick="document.getElementById('formContragent').submit();">Сохранить</a>
						</div>
						<div class="button">
							<a onclick='history.back()'>Отмена</a>
						</div>
					</div>						
				</form>
		</div>
	</div>
</body>
</html>