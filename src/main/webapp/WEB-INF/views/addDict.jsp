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
			<div class='menu_item'><a href='${pageContext.request.contextPath}/contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item menu_selected'><a href='${pageContext.request.contextPath}/dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
			<div class="filters">
				<span class="filter label">Справочники:</span>
<%-- 				<span class="filter <c:choose><c:when test="${dict=='address'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="${pageContext.request.contextPath}/dictionaries/address">Адрес</a>
				</span> --%>
				<span class="filter <c:choose><c:when test="${dict=='transport'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="${pageContext.request.contextPath}/dictionaries/transport">Транспорт</a>
				</span>
				<span class="filter <c:choose><c:when test="${dict=='product'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="${pageContext.request.contextPath}/dictionaries/product">Товар</a>
				</span>
				<span class="filter <c:choose><c:when test="${dict=='store'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="${pageContext.request.contextPath}/dictionaries/store">Склад</a>
				</span>
			</div>
		</div>
		<div class="main_table">
				<form class="form-signin" name='f' action="<c:if test="${!empty actionType}">${actionType}</c:if>" method='POST' id="forms">
				<c:if test="${!empty dictionary}">
					<input type="hidden" name="id" value='<c:choose>
															<c:when test="${dict=='address'}">${dictionary.addressId}</c:when>
															<c:when test="${dict=='transport'}">${dictionary.transportId}</c:when>
															<c:when test="${dict=='product'}">${dictionary.productId}</c:when>
															<c:when test="${dict=='store'}">${dictionary.storeId}</c:when>
														  </c:choose>'/>
				</c:if>
					<p class="fieldrow">
						<label class="fieldlabel">Наименование</label>
						<input class="fieldfld" type="text" required name="value" value="<c:if test="${!empty dictionary}">${dictionary.name}</c:if>"/>
					</p>	
					<div class="buttons">
						<div class="button">
							<a href="javascript:{}" onclick="document.getElementById('forms').submit();">Сохранить</a>
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