<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css"/>"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var today = new Date();
			var todayString = today.getFullYear()+ '-' + (today.getMonth() < 9 ? ('0'+ (today.getMonth() + 1)) : (today.getMonth() + 1))+ '-' + (today.getDate() < 10 ? ('0' + today.getDate()) : today.getDate());
			$('.fielddate').datetimepicker({
				lang:'ru',
				timepicker:false,
				format:'Y-m-d',
				closeOnDateSelect:true,
				value:todayString
			});
		} );		
	</script>	
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
	<div class="main_data">
		<div class="main_table">
				<h3>Поступление товара</h3>
				<form class="form-signin" name='fi' action="report/formIncoming" method='POST' id="formIncoming">
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Период с</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="dateStart" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Период по</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="dateEnd" />
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useContragent"/>
						<label class="fieldshortlabel">Контрагент</label>
						<select class="fieldcombo" size="1" required name="contragent" id="contragent">
							<c:if test="${!empty contragentList}">
								<c:forEach items="${contragentList}" var="contragent">
									<option value="${contragent.contragentId}">${contragent.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useProduct"/>
						<label class="fieldshortlabel" for="product">Товар</label>
						<select class="fieldcombo" size="1" required name="product">
							<c:if test="${!empty productList}">
								<c:forEach items="${productList}" var="product">
									<option value="${product.productId}">${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useStore"/>						
						<label class="fieldshortlabel" for="store">Склад</label>
						<select class="fieldcombo" size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}">${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</p>
					<div class="buttons">
						<div class="button">
							<a href="javascript:{}" onclick="document.getElementById('formIncoming').submit();">Сформировать</a>
						</div>
					</div>						
				</form>
				<h3>Отгрузка товара</h3>
				<form class="form-signin" name='fs' action="report/formShipment" method='POST' id="formShipment">
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Период с</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="dateStart" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Период по</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="dateEnd" />
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useContragent"/>
						<label class="fieldshortlabel">Контрагент</label>
						<select class="fieldcombo" size="1" required name="contragent" id="contragent">
							<c:if test="${!empty contragentList}">
								<c:forEach items="${contragentList}" var="contragent">
									<option value="${contragent.contragentId}">${contragent.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useProduct"/>
						<label class="fieldshortlabel" for="product">Товар</label>
						<select class="fieldcombo" size="1" required name="product">
							<c:if test="${!empty productList}">
								<c:forEach items="${productList}" var="product">
									<option value="${product.productId}">${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useStore"/>						
						<label class="fieldshortlabel" for="store">Склад</label>
						<select class="fieldcombo" size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}">${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="useTransport"/>						
						<label class="fieldshortlabel" for="transport">Транспорт</label>
						<select class="fieldcombo" size="1" required name="transport">
							<c:if test="${!empty transportList}">
									<c:forEach items="${transportList}" var="transport">
										<option value="${transport.transportId}">${transport.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<input class="fieldstartcheck" type="checkbox" name="usePaymentType"/>						
						<label class="fieldshortlabel" for="paymentType">Тип оплаты</label>
						<input type="radio" name="paymentType" value="true">Наличный
   						<input type="radio" name="paymentType" value="false" checked="checked">Безналичный
					</p>
					<div class="buttons">
						<div class="button">
							<a href="javascript:{}" onclick="document.getElementById('formShipment').submit();">Сформировать</a>
						</div>
					</div>						
				</form>
				<h3>Остаток на складе</h3>
				<form class="form-signin" name='fs' action="report/formBalance" method='POST' id="formBalance">
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Период с</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="dateStart" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Период по</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="dateEnd" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="product">Товар</label>
						<select class="fieldcombo" size="1" required name="product">
							<c:if test="${!empty productList}">
								<c:forEach items="${productList}" var="product">
									<option value="${product.productId}">${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="store">Склад</label>
						<select class="fieldcombo" size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}">${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</p>
					<div class="buttons">
						<div class="button">
							<a href="javascript:{}" onclick="document.getElementById('formBalance').submit();">Сформировать</a>
						</div>
					</div>						
				</form>
		</div>
	</div>
</body>
</html>