<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.1/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="http://cdn.datatables.net/tabletools/2.2.2/js/dataTables.tableTools.min.js"></script>
	<script type="text/javascript">
	
	$.extend( $.fn.dataTable.defaults, {
	    "searching": false,
	    "ordering": false
	} );
	
		$(document).ready(function() {			
		    $('#data_table').dataTable( {
		        "dom": "Tlfrtip",
		        "processing": true,
		        "serverSide": true,
		        "ajax":{
		        	 "url" :"contractor/getList",
		        	 "type" : "POST"
		        },
		        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Все"] ],
		        columns: [
		                  { data: null, defaultContent: "", orderable: false },
		                  { data: null, data : "name" },
		                  { data: "address" }
		              ],
		        tableTools: {
		            sRowSelect: "os",
		            sRowSelector: 'td:first-child',
		        	aButtons: [
					]
		        }
		    } );
		} );
	</script>
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
	<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>"/> --%>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="http://cdn.datatables.net/1.10.1/css/jquery.dataTables.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="http://cdn.datatables.net/tabletools/2.2.2/css/dataTables.tableTools.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="http://editor.datatables.net/media/css/dataTables.editor.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css"/>"/>
</head>	
<body>
	<div class="top_data"></div>
	<div class="long_width">
		<div class="main_menu">
			<div class='menu_item menu_selected'><a href='contractor'><spring:message code="menu.contractor"/></a></div>
			<div class='menu_item'><a href='incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item'><a href='dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
			<div class="filters">
				<span class="filter label">Фильтр:</span>
				<span class="filter inactive"><a href="">Все</a></span>
				<span class="filter inactive"><a href="">Новые</a></span>
				<span class="filter active"><a href="">Активные</a></span>
				<span class="filter inactive"><a href="">Забаненые</a></span>
				<span class="filter inactive"><a href="">Провереные</a></span>
				<span class="filter inactive"><a href="">Потенциальные</a></span>
			</div>
		</div>
		<div class="main_table">
			<table id="data_table" class="display cell-border compact" cellspacing="0" width="100%">
		        <thead>
		            <tr class="head">                
		            	<th>
 
		                </th>
		                <th>Контрагент</th>
		                <th>Пункт отгрузки</th>
		            </tr>
		        </thead>
		    </table>
			<div class="buttons">
				<div class="button">Добавить</div>
				<div class="button">Удалить</div>
				<div class="button">Забанить</div>
			</div>
		</div>
	</div>
</body>
</html>