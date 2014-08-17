<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css"/>"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var today = new Date();
			var todayString = today.getFullYear()+ '-' + (today.getMonth() < 9 ? ('0'+ (today.getMonth() + 1)) : (today.getMonth() + 1))+ '-' + (today.getDate() < 10 ? ('0' + today.getDate()) : today.getDate());
			$('#datetimepicker').datetimepicker({
				lang:'ru',
				timepicker:false,
				format:'Y-m-d',
				closeOnDateSelect:true,
				value:todayString
			});
		} );		
	</script>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item '><a href='${pageContext.request.contextPath}/contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item menu_selected'><a href='${pageContext.request.contextPath}/incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item '><a href='${pageContext.request.contextPath}/dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
				<form class="form-signin" name='f' action="save" method='POST' id="forms">
					<div>
						<label for="date">Дата</label>
							<input type="text" required id="datetimepicker" name="date" />
					</div>
					<div>
						<label >Поставщик</label>
						<select size="1" required name="contragent">
							<c:if test="${!empty contragentList}">
								<c:forEach items="${contragentList}" var="contragent">
									<option value="${contragent.contragentId}">${contragent.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div>
						<label for="product">Товар</label>
						<select size="1" required name="product">
							<c:if test="${!empty productList}">
								<c:forEach items="${productList}" var="product">
									<option value="${product.productId}">${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div>
						<label for="productCount">Колво товара</label>
							<input type="number" step="0.01" required id="productCount" name="productCount" />
					</div>
					<div>
						<label for="store">Склад</label>
						<select size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}">${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</div>
					<textarea name="comment"></textarea>
					<div>
						<input type="submit" value="Сохранить" />
					</div>						
				</form>
		</div>
	</div>
</body>
</html>