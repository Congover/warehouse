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
			changeAddressSelect();
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
		
		function changeAddressSelect(){
			var cId = $("select#contragent").val();
			$("#address").empty();
			$.post('${pageContext.request.contextPath}/contractor/getAdresses', {contragentId: cId}, function(data) {
				if(data.length != 0) {
					$.each(data, function(key, value) {
						/* $("#address").append($("", {
							value: key,
							text: value
						})); */
						$('#address').append($('<option value="' + key + '">'+ value+ '</option>'));
					});
					}
				});
		};
	</script>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item'><a href='${pageContext.request.contextPath}/contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item menu_selected'><a href='${pageContext.request.contextPath}/shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='${pageContext.request.contextPath}/report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item '><a href='${pageContext.request.contextPath}/dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
				<form class="form-signin" name='f' action="save" method='POST' id="forms">
					<p class="fieldrow">
						<label class="fieldlabel" for="date">Дата</label>
						<input class="fielddate" type="text" required id="datetimepicker" name="date" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Покупатель</label>
						<select class="fieldcombo" size="1" required name="contragent" id="contragent" onchange="changeAddressSelect()">
							<c:if test="${!empty contragentList}">
								<c:forEach items="${contragentList}" var="contragent">
									<option value="${contragent.contragentId}">${contragent.name}</option>
								</c:forEach>
							</c:if>
						</select>
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
						<label class="fieldlabel" for="productCount">Колво товара</label>
						<input class="fieldnum" type="number" required id="productCount" name="productCount" />
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="address">Пункт</label>
						<select class="fieldcombo" size="1" required name="address" id="address">
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
					<p class="fieldrow">
						<label class="fieldlabel">Тип оплаты</label>
						<input type="radio" name="paymentType" value="true">Наличный
   						<input type="radio" name="paymentType" value="false" checked="checked">Безналичный
					</p>
					<p class="fieldrow">
						<label class="fieldlabel" for="transport">Транспорт</label>
						<select class="fieldcombo" size="1" required name="transport">
							<c:if test="${!empty transportList}">
									<c:forEach items="${transportList}" var="transport">
										<option value="${transport.transportId}">${transport.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</p>
					<p class="fieldrowcomment">
						<textarea class="fieldtext" name="comment"></textarea>
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