<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
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
					<div>
						<label for="date">Период с</label>
						<input type="text" required id="datetimepicker" name="dateStart" />
						<label for="date">Период по</label>
						<input type="text" required id="datetimepicker" name="dateEnd" />
					</div>
					<div>
						<input type="checkbox" name="useContragent"/>
						<label>Контрагент</label>
						<select size="1" required name="contragent" id="contragent" onchange="changeAddressSelect()">
							<c:if test="${!empty contragentList}">
								<c:forEach items="${contragentList}" var="contragent">
									<option value="${contragent.contragentId}">${contragent.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div>
						<input type="checkbox" name="useProduct"/>
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
						<input type="checkbox" name="useStore"/>						
						<label for="store">Склад</label>
						<select size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}">${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</div>
					<div>
						<input type="submit" value="Сформировать" />
					</div>						
				</form>
				<h3>Отгрузка товара</h3>
				<form class="form-signin" name='fs' action="report/formShipment" method='POST' id="formShipment">
					<div>
						<label for="date">Период с</label>
						<input type="text" required id="datetimepicker" name="dateStart" />
						<label for="date">Период по</label>
						<input type="text" required id="datetimepicker" name="dateEnd" />
					</div>
					<div>
						<input type="hidden" value="on" name="_useContragent"/>
						<input type="checkbox" name="useContragent"/>
						<label>Контрагент</label>
						<select size="1" required name="contragent" id="contragent" onchange="changeAddressSelect()">
							<c:if test="${!empty contragentList}">
								<c:forEach items="${contragentList}" var="contragent">
									<option value="${contragent.contragentId}">${contragent.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div>
						<input type="hidden" value="on" name="_useProduct"/>
						<input type="checkbox" name="useProduct"/>
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
						<input type="hidden" value="on" name="_useStore"/>
						<input type="checkbox" id="useStore" name="useStore"/>						
						<label for="store">Склад</label>
						<select size="1" required name="store">
							<c:if test="${!empty storeList}">
									<c:forEach items="${storeList}" var="store">
										<option value="${store.storeId}">${store.name}</option>
									</c:forEach>
							</c:if>
						</select>
					</div>
					<div>
						<input type="submit" value="Сформировать" />
					</div>						
				</form>
		</div>
	</div>
</body>
</html>