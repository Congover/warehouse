<!DOCTYPE html>
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
	    "searching": true,
	    "ordering": false
	} );
	
		$(document).ready(function() {
			var selected;		
			var table =  $('#data_table').dataTable( {
		    	"language": {
		    		"url" : "resources/russ.lang"
		    	},
		        "dom": "Tlrtip",
		        "processing": true,
		        "serverSide": true,
		        "ajax":{
		        	 "url" :"incoming/getList",
		        	 "type" : "POST"
		        },
	              "stateSave": false,
		        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Все"] ],
		        columns: [
		                  { data : "date" },
		                  { data: "contractor" },
		                  { data: "product" },
		                  { data: "count" },
		                  { data: "store" },
		                  { data: "comment" }
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
		        location.href = 'incoming/edit/' + selected
		    } );		    
		    $('#btnDelete').click( function () {
		        if(selected == undefined || selected == null) {
		        	alert("Выберите Строку!");
		        	return;
		        }
		        $.post("incoming/delete", {id : selected}, function(data){
		        	if(data) {
		        		selected = null;
		        		$('#data_table').DataTable().row('.selected').remove().draw( false );
		        	} else {
		        		alert('Невозможно удалить объект!');
		        	}		        	
		        });
		    } );
		} );
		
		function filterContragent(){
			var cId = $("select#contragent").val();
			$("#address").empty();
			$.post('${pageContext.request.contextPath}/incoming/changeContragent', {id: cId}, function(data) {
				if(data) {
					selected = null;
				    $('#data_table').DataTable().search( 
				    		cId
				        ).draw();
					
					}
				});
		};
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
			<div class='menu_item menu_selected'><a href='incoming'><spring:message code="menu.incoming"/></a></div>
			<div class='menu_item'><a href='shipment'><spring:message code="menu.shipment"/></a></div>
			<div class='menu_item'><a href='packing'><spring:message code="menu.packing"/></a></div>
			<div class='menu_item'><a href='report'><spring:message code="menu.report"/></a></div>
			<div class='menu_item'><a href='dictionaries'><spring:message code="menu.dictionaries"/></a></div>	
		</div>
	</div>
	<div class="main_data">
		<div class="main_table">
			<p class="fieldrow">
				<label class="fieldlabel">Контрагент</label>
				<select class="fieldcombo" size="1" name="contragent" id="contragent" onchange="filterContragent()">
					<option selected></option>
					<c:if test="${!empty contragentList}">
						<c:forEach items="${contragentList}" var="contragent">
							<option value="${contragent.contragentId}"<c:if test="${!empty incContrId && incContrId == contragent.contragentId}">selected</c:if> >${contragent.name}</option>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<table id="data_table" class="display cell-border compact">
		        <thead>
		            <tr class="head">
		                <th><spring:message code="incoming.table.header.date"/></th>
		                <th><spring:message code="incoming.table.header.contractor"/></th>
		                <th><spring:message code="incoming.table.header.product"/></th>
		                <th><spring:message code="incoming.table.header.count"/></th>
		                <th><spring:message code="incoming.table.header.store"/></th>
		                <th><spring:message code="incoming.table.header.comment"/></th>
		            </tr>
		        </thead>
		    </table>
			<div class="buttons">
				<div class="button"><a href="incoming/add"><spring:message code="btn.add"/></a></div>
				<div id="btnChange" class="button"><spring:message code="btn.change"/></div>
				<div id="btnDelete" class="button"><spring:message code="btn.delete"/></div>
			</div>
		</div>
	</div>
</body>
</html>