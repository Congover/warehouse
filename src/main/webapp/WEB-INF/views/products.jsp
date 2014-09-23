<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.1/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="http://cdn.datatables.net/tabletools/2.2.2/js/dataTables.tableTools.min.js"></script>
	<script type="text/javascript">
	
	$.extend( $.fn.dataTable.defaults, {
	    "searching": false,
	    "ordering": false
	} );
	
		$(document).ready(function() {
			var selected;		
			var table = $('#data_table').dataTable( {
		    	"language": {
		    		"url" : "${pageContext.request.contextPath}/resources/russ.lang"
		    	},
		        "dom": "Tlfrtip",
		        "processing": true,
		        "serverSide": true,
		        "ajax":{
		        	 "url" :"dictionaries/getList",
		        	 "type" : "POST"
		        },
		        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Все"] ],
		        columns: [
		                  { data: "name" },
		                  { data: "parentName"}
		              ],
		        tableTools: {
		        	aButtons: [
					]
		        }
		    } );
			
		    $('#data_table tbody').on( 'click', 'tr', function () {
		        if ( $(this).hasClass('selected') ) {
		        	selected = null;
		            $(this).removeClass('selected');
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		            selected = this.id;
		        }
		    } );		    
		    $('#btnChange').click( function () {
		        if(selected == undefined || selected == null) {
		        	alert("Выберите объект!");
		        	return;
		        }
		        location.href = 'dictionaries/edit/' + selected
		    } );		    
		    $('#btnDelete').click( function () {
		        if(selected == undefined || selected == null) {
		        	alert("Выберите объект!");
		        	return;
		        }
		        $.post("dictionaries/delete", {id : selected}, function(data){
		        	if(data) {
		        		selected = null;
		        		$('#data_table').DataTable().row('.selected').remove().draw( false );
		        	} else {
		        		alert('Невозможно удалить объект!');
		        	}		        	
		        });
		    } );
		} );
	</script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="http://cdn.datatables.net/1.10.1/css/jquery.dataTables.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="http://cdn.datatables.net/tabletools/2.2.2/css/dataTables.tableTools.css"/>"/>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item'><a href='contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item menu_selected'><a href='dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
			<div class="filters">
				<span class="filter label">Справочники:</span>
				<span class="filter inactive">
					<a href="dictionaries/address">Адрес</a>
				</span>
				<span class="filter inactive">
					<a href="dictionaries/transport">Транспорт</a>
				</span>
				<span class="filter active">
					<a href="dictionaries/product">Товар</a>
				</span>
				<span class="filter inactive">
					<a href="dictionaries/store">Склад</a>
				</span>
			</div>
		</div>
		<div class="main_table">
			<table id="data_table" class="display cell-border compact" cellspacing="0" width="100%">
		        <thead>
		            <tr class="head">
		                <th>Наименование</th>
		                <th>Группа</th>
		            </tr>
		        </thead>
		    </table>
			<div class="buttons">
				<div class="button"><a href="dictionaries/addProduct">Добавить</a></div>
				<div id="btnChange" class="button">Изменить</div>
				<div id="btnDelete" class="button">Удалить</div>
			</div>
		</div>
	</div>
</body>
</html>