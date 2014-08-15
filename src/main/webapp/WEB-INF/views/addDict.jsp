<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<style type="text/css">
		table.dataTable tr td:first-child {
		text-align: center;
		}
		table.dataTable tr td:first-child:before {
		content: "\f096"; /* fa-square-o */
		font-family: FontAwesome;
		}
		table.dataTable tr.selected td:first-child:before {
		content: "\f046"; /* fa-check-square-o */
		}
	</style>
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
				<span class="filter <c:choose><c:when test="${dict=='address'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="address">Адрес</a>
				</span>
				<span class="filter <c:choose><c:when test="${dict=='transport'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="transport">Транспорт</a>
				</span>
				<span class="filter <c:choose><c:when test="${dict=='product'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="product">Товар</a>
				</span>
				<span class="filter <c:choose><c:when test="${dict=='store'}">active</c:when><c:otherwise>inactive</c:otherwise></c:choose>">
					<a href="store">Склад</a>
				</span>
			</div>
		</div>
		<div class="main_table">
				<form class="form-signin" name='f' action="addDictionary" method='POST' id="forms">
					<div>
						<label>Наименование</label>
						<div class="controls">
							<input type="text" required name="value" />
						</div>
					</div>
					<c:if test="${dict=='product'}">
					<div>
						<label for="productType">Тип</label>
						<select size="1" required name="productType">
							<option value="0">Мешок</option>
							<option value="1">в мешках</option>
							<option value="2">россыпью</option>
						</select>
					</div>
					</c:if>
					
					<div>
						<input type="submit" value="Сохранить" />
					</div>						
				</form>
		</div>
	</div>
</body>
</html>