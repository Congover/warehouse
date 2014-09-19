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
				<form class="form-signin" name='f' action="<c:if test="${!empty actionType}">${actionType}</c:if>" method='POST' id="formContragent">
				<input type="hidden" name="id" value="<c:if test="${!empty contragent}">${contragent.contragentId}</c:if>"></input>
					<p class="fieldrow">
						<label class="fieldlabel">Наименование</label>
						<input class="fieldfld" type="text" required name="value" value="<c:if test="${!empty contragent}">${contragent.name}</c:if>" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Адрес</label>
						<select class="fieldcombo" size="1" name="address1">
							<option selected></option>
							<c:if test="${!empty addressList}">
								<c:forEach items="${addressList}" var="address">
									<option value="${address.addressId}" <c:if test="${!empty address0 && address0 == address.addressId}">selected</c:if> >${address.fullAddress}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<select class="fieldcombo" size="1" name="address2">
							<option selected></option>
							<c:if test="${!empty addressList}">
								<c:forEach items="${addressList}" var="address">
									<option value="${address.addressId}" <c:if test="${!empty address1 && address1 == address.addressId}">selected</c:if> >${address.fullAddress}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<select class="fieldcombo" size="1" name="address3">
							<option selected></option>
							<c:if test="${!empty addressList}">
								<c:forEach items="${addressList}" var="address">
									<option value="${address.addressId}" <c:if test="${!empty address2 && address2 == address.addressId}">selected</c:if> >${address.fullAddress}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<select class="fieldcombo" size="1" name="address4">
							<option selected></option>
							<c:if test="${!empty addressList}">
								<c:forEach items="${addressList}" var="address">
									<option value="${address.addressId}" <c:if test="${!empty address3 && address3 == address.addressId}">selected</c:if> >${address.fullAddress}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Доп. адрес</label>
						<select class="fieldcombo" size="1" name="address5">
							<option selected></option>
							<c:if test="${!empty addressList}">
								<c:forEach items="${addressList}" var="address">
									<option value="${address.addressId}" <c:if test="${!empty address4 && address4 == address.addressId}">selected</c:if> >${address.fullAddress}</option>
								</c:forEach>
							</c:if>
						</select>
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