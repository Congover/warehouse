<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="com.wh.entity.ProductType" %> --%>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
	<script type="text/javascript">
		$(document).ready(function() {   
			checkChecBoxState();
		    $('#isGroup').click( function () {
		    	checkChecBoxState();
		    } );
			
		} );
		function checkChecBoxState() {
    	  if($('#isGroup').prop("checked")){ 
    		  $('#groupList').hide();
   		  }else{  
    		  $('#groupList').show();
   		  }
		};
	</script>
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
<!-- 				<span class="filter inactive"> -->
<%-- 					<a href="${pageContext.request.contextPath}/dictionaries/address">Адрес</a> --%>
<!-- 				</span> -->
				<span class="filter inactive">
					<a href="${pageContext.request.contextPath}/dictionaries/transport">Транспорт</a>
				</span>
				<span class="filter active">
					<a href="${pageContext.request.contextPath}/dictionaries/product">Товар</a>
				</span>
				<span class="filter inactive">
					<a href="${pageContext.request.contextPath}/dictionaries/store">Склад</a>
				</span>
			</div>
		</div>
		<div class="main_table">
				<form class="form-signin" name='f' action="<c:if test="${!empty actionType}">${actionType}</c:if>" method='POST' id="forms">
				<c:if test="${!empty dictionary}">
					<input type="hidden" name="id" value='${dictionary.productId}'/>
				</c:if>
					<p class="fieldrow">
						<label class="fieldlabel">Наименование</label>
						<input class="fieldfld" type="text" required name="value" value="<c:if test="${!empty dictionary}">${dictionary.name}</c:if>"/>
					</p>
					<p class="fieldrow">
						<label class="fieldlabel">Группа товаров</label>
						<input type="checkbox" <c:if test="${!empty dictionary && dictionary.productType == 'GROUP' }">checked</c:if> 
						<c:if test="${!empty dictionary}">disabled</c:if> id="isGroup" name="isGroup"/>
					</p>
					<p class="fieldrow" id="groupList">
						<label class="fieldlabel">Группа</label>
						<select class="fieldcombo" size="1" name="groupId" id="groupId">
							<option selected></option>
							<c:if test="${!empty groupList}">
								<c:forEach items="${groupList}" var="product">
									<option value="${product.productId}" <c:if test="${!empty dictionary && !empty dictionary.parent && dictionary.parent.productId == product.productId}">selected</c:if> >${product.name}</option>
								</c:forEach>
							</c:if>
						</select>
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