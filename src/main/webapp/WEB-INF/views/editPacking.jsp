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
			$('#datetimepicker').datetimepicker({
				lang:'ru',
				timepicker:false,
				format:'Y-m-d',
				closeOnDateSelect:true
			});
		} );		
	</script>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item '><a href='${pageContext.request.contextPath}/contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item menu_selected'><a href='${pageContext.request.contextPath}/packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item '><a href='${pageContext.request.contextPath}/dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
				<form class="form-signin" name='f' action="../update" method='POST' id="forms">
					<input type="hidden" name="id" value='${current.packingId}'/>
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Дата</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="date" value="${current.createDate}"/>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="product">Товар(россыпью)</label>
						<select class="fieldcombo" size="1" required name="product">
							<c:if test="${!empty productList}">
								<c:forEach items="${productList}" var="product">
									<option value="${product.productId}"<c:if test="${!empty current.product && current.product.productId == product.productId}">selected</c:if> >${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="product">Товар(мешки)</label>
						<select class="fieldcombo" size="1" required name="packedProduct">
							<c:if test="${!empty productList}">
								<c:forEach items="${productList}" var="product">
									<option value="${product.productId}"<c:if test="${!empty current.packedProduct && current.packedProduct.productId == product.productId}">selected</c:if> >${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="store">Склад</label>
						<select class="fieldcombo" size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}" <c:if test="${!empty current.store && current.store.storeId == store.storeId}">selected</c:if> >${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</p>
										<p class="fieldrow">
						<label class="fieldlabel" for="productCount">Колво товара</label>
						<input class="fieldnum" type="number" step="0.1" required id="productCount" name="productCount" value="${current.productCount}" />
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