$(function(){
	var updateIconsSize = function(){
		var el = $('.nav-bar-button').eq(0);
		var size = el.width();
		var sizetext = $('.nav-bar-text-left').height();		
		$('.nav-bar-height-butt').height(size);		
		$('.nav-bar-text').height(sizetext);
		$('.nav-bar').height(size / 0.7  + sizetext);
		$('.nav-bar-button').each(function(index, iconEl){
			$(iconEl).height(size);
		});
		$('.nav-bar-button-right').each(function(index, iconEl){
			$(iconEl).height(size);
		});
		$('.nav-bar-butt-center-big').each(function(index, iconEl){
			$(iconEl).height(size);
		});
		$('.popap-butt-center-big').each(function(index, iconEl){
			$(iconEl).height(size);
			$(iconEl).width($('.nav-bar-butt-center-big').width());
		});
		$('#popap-el').height($('#menu-button').height() * 2.5);
		$('#popap-el').width($('#menu-button').width());
		$('#popap-el').css("margin-top", $('#menu-button').height());
		$('.popap-img').each(function(index, iconEl){
			$(iconEl).height(size);
		});
		$('#.popap-img-one').height(size);
	};
	
	$(window).resize(function() {
		updateIconsSize();  
	});
	
	updateIconsSize();
});

function home() {
	document.location.href =(getHostUrl());
};

function link(str) {
	location.href = (str);
};

function linkproduct(str) {
	location.href =location + '/producttype/' + str;	
};

function buttredir(str) {
	location.href = getHostUrl() + str;
};

function getHostUrl() {
	var url = location.protocol+ '//' + location.host + '/Sandals';
	return url;
};

function shopinfodialig() {
	document.getElementById("productview").style.display="block";	
	$('.productview-element').each(function(index, iconEl){
		$(iconEl).height($( '#product-view-height' ).height());
	});	
	
}

function getdata(id) {
	$.post(getHostUrl() + '/createorder/getjsonproduct/' + id, function(data) {
		  setdata(data);
		  show();
		}, "json");				
};

function setdata(data) {
	$( '#productname' ).html( data.productName);
	$( '#productprice' ).html(data.priceWithVat);
	$( '#productcount' ).val( data.productSelected );
	$( '#productavailible' ).html( data.productAvailible );
	$( '#productsumm' ).html( data.summWithVat );
	$( '#idproduct' ).val( data.productId );
	$( '#nav-bar-productname' ).html( data.productName );
};

function show(){
	$(window).width();
	$(document).width();
	document.getElementById("inputbox").style.display="block";
	document.getElementById("semidiv").style.display="block";
};

function hide(){
		document.getElementById("inputbox").style.display="none";
		document.getElementById("semidiv").style.display="none";
		$( '#productcount' ).removeClass("b-red");	
};

function showcreateorder(){
	$.post(getHostUrl() + '/createorder/valbasket', {}, function(data) {
		if(data.length == 0) {			
			$( '#dialogcount' ).html($( '#ordercount' ).html() );
			$( '#dialogsumm' ).html($( '#ordersumm' ).html() );
				document.getElementById("dialog").style.display="block";
				document.getElementById("semidiv").style.display="block";			
		} else {
			$( '#order-body').prepend("<div id='main-error-div' class='main-error-div'></div>");
			$( '#main-error-div' ).append("<img src='/Sandals/resources/img/attention.png' style='float: left;margin-top: 2px;'>");
			$( '#main-error-div' ).append("<div id='error-div' class='error-element'></div>");
			$( '#error-div' ).html('На складе недостаточно позиций для '+ data.length + ' товаров.');
			
			
		}
	});
};

function sendproduct() {
	var selected = $( '#productcount' ).val();
	var productid = $( '#idproduct' ).val();
	if(recheck()) {
	$.post(getHostUrl() + '/createorder/adddd/', {prId: productid, prS: selected}, function(data) {
		var incorrect = data.search('incorrectProduct');
		if(incorrect == 0) {
			location.href = getHostUrl() + 	data;		
		} else {
		var incorrectcount = data.search('maxcount:');
		if(incorrectcount == 0) {
		var maxcount = data.split('maxcount:')[1];
			$( '#productcount' ).addClass("b-red");
			$( '#productavailible' ).html( maxcount );				
		} else {
			location.href = getHostUrl() + data;
		}}});
	}
};

function remove() {
	var productid = $( '#idproduct' ).val();
	var selected = 0;
	$.post(getHostUrl() + '/createorder/adddd/', {prId: productid, prS: selected}, function(data) {
		var incorrect = data.search('incorrectProduct');
		if(incorrect == 0) {
			location.href = getHostUrl() + 	data;		
		} else {
			location.href = getHostUrl() + data;
		}});					
};

function recheck() {
	var productid = $( '#idproduct' ).val();
	$.post(getHostUrl() + '/createorder/getjsonproduct/' + productid, function(data) {
		$( '#productname' ).html( data.productName);
		$( '#productprice' ).html(data.priceWithVat);
		$( '#productavailible' ).html( data.productAvailible );
		}, "json");	
	var selected = parseInt($( '#productcount' ).val());
	if(selected == 0) {
		$( '#productcount' ).val('0');		
	}
	var availible = parseInt($( '#productavailible' ).html());
	if(selected >= 0 && availible >= selected) {
		var price = parseInt($( '#productprice' ).html());
		$( '#productsumm' ).html( selected *  price);
		$( '#productcount' ).removeClass("b-red");
		return true;
	} else {
		$( '#productcount' ).addClass("b-red");
		return false;
		
	}				
};

function nocreate() {
	document.getElementById("dialog").style.display="none";
	document.getElementById("semidiv").style.display="none";				
};

function create() {
	var url = getHostUrl() + '/createorder/shop/createorder';
	$.post(url, function(data) {
		allert(data);
	});	
	document.getElementById("dialog").style.display="none";
	document.getElementById("semidiv").style.display="none";
	location.href = location.protocol+ '//' + location.host + '/Sandals/';
};

//FIXME
function showpopap(loc, shopId) {
	document.getElementById("semidiv").style.display="block";
	if('stat' ==loc) {
		$('#main').append("<div id='popap'></div>");
		$('#popap').append("<div class='popap-butt-center-big button-button' id='popap-button' onclick='radd()'><p class='nav-butt-c-name'>" + $('#button-name').html() +  "</p></div>");
		$('#popap-button').height($('#menu-button').height());
		$('#popap-button').width($('#menu-button').width());
		$('#popap').append("<div class='popap-el' id='popap-el'></div>");
		$('#popap-el').css("margin-top", $('#menu-button').height());
		$('#popap-el').width($('#menu-button').width());
		$('#popap-el').height($('#menu-button').height() * 2.5);
		$('#popap-el').append("<div style='height: 47.5%; width: 100%'></div>");
		$('#popap-el').append("<div class='popap-img button-list' id='stat'>");		
		$('#stat').height($('#nav-button-home').height());	
		$('#stat').click(function() {
			radd();
			buttredir('/createorder/shop/' + shopId + '/stat');});
		$('#popap-el').append("<div class='popap-img button-star' id='popul'>");	
		$('#popul').height($('#nav-button-home').height());	
		$('#popul').click(function() {
			radd();
			buttredir('/createorder/shop/' + shopId + '/pop');});
		$('#popap-el').append("<div class='popap-img button-dollar' id='fin'>");	
		$('#fin').height($('#nav-button-home').height());
		$('#fin').click(function() {
			radd();
			buttredir('/createorder/shop/' + shopId + '/fin');});;
		$('#popap-el').append("<div class='popap-img button-info' id='about'>");	
		$('#about').height($('#nav-button-home').height());	
		$('#about').click(function() {
			radd();
			buttredir('/createorder/shop/' + shopId + '/about');});
	
		$('#popap').addClass("display");
	}
	if('order' == loc) {
		$('#main').append("<div id='popap'></div>");
		$('#popap').append("<div class='nav-bar-butt-center1' onclick='radd()'><p class='nav-bar-butt-center'>" + $('#button-name').html() +  "</p></div>");
		$('#popap').append("<div class='popap-el' id='popap-el'></div>");
		$('#popap-el').append("<img class='popap'  id='stat' src='/Sandals/resources/img/statistics.png'>");
		$('#stat').click(function() {
			radd();
			buttredir('/createorder/shop/' + shopId + '/stat');});
		$('#popap-el').append("<img class='popap' id='popul' src='/Sandals/resources/img/shops.png'>");
		$('#popul').click(function() {
			radd();
			buttredir('/createorder/cities');});	

		$('#popap').addClass("display");
	}
	
	if('stock' == loc) {
		$('#main').append("<div id='popap'></div>");
		$('#popap').append("<div class='popap-butt-center-big button-button' id='popap-button' onclick='radd()'><p class='nav-butt-c-name'>" + $('#button-name').html() +  "</p></div>");
		$('#popap-button').height($('#menu-button').height());
		$('#popap-button').width($('#menu-button').width());
		$('#popap').append("<div class='popap-el' id='popap-el'></div>");
		$('#popap-el').css("margin-top", $('#menu-button').height());
		$('#popap-el').width($('#menu-button').width());
		$('#popap-el').height($('#menu-button').height() * 2.5);
		$('#popap-el').append("<div style='height: 47.5%; width: 100%'></div>");		
		$('#popap-el').append("<div class='popap-img-one button-list'  id='list'>");	
		$('#list').height($('#nav-button-home').height());
		$('#list').click(function() {
			radd();
			buttredir('/stock/productlist');});
		$('#popap').addClass("display");
	}
}

//FIXME
function adda() {
	document.getElementById("semidiv").style.display="block";
	$('#main').append("<div id='popap'></div>");
	$('#popap').append("<div class='nav-bar-butt-center1' onclick='radd()'><p class='nav-bar-butt-center'>" + $('#button-name').html() +  "</p></div>");
	$('#popap').append("<div id='popap-el' style='background-color: #fff;'> <img src='/Sandals/resources/img/statistics.png' onclick='showcreateorder()/></div>");
	$('#popap-el').append("<img src='/Sandals/resources/img/statistics.png'>");
	$('#popap-el').append("<img src='/Sandals/resources/img/shops.png'>");
	
	$('#popap').addClass("display");
}
//FIXME
function radd() {
	$('#popap').remove();
	document.getElementById("semidiv").style.display="none";	
}

function viewproductdescript(id) {
	$.post(getHostUrl() + '/createorder/getjsonproduct/' + id, function(data) {
		fillprodviewdata(data);
		shopinfodialig();
		showsemidiv();
		}, "json");	
};

function fillprodviewdata(data) {
	$( '#pv-name' ).html( data.productName);
	$( '#pv-production' ).html(data.country);
	$( '#pv-description' ).html(data.description);
	$( '#pv-pack' ).html( data.packageType + ' по ' + data.capacity + data.capacityUnit);
	$( '#pv-inbox' ).html( data.unitsInBox + ' ' + data.packageType);
	var unitInBox = parseInt(data.unitsInBox);
	var count = parseInt(data.productAvailible);
	var countBox = parseInt(count/unitInBox);
	$( '#pv-instock' ).html( data.productAvailible + ' ' + data.packageType + ' / ' + countBox + ' ящиков');
	$( '#pv-summw' ).html( data.priceWithoutVat + ', без НДС');
	$( '#pv-summ' ).html( data.priceWithVat + ', с НДС');
};

function showsemidiv() {
	document.getElementById("semidiv").style.display="block";	
}

function hideviewproduct() {
	document.getElementById("semidiv").style.display="none";
	document.getElementById("productview").style.display="none";	
}

function validateloginpage() {
	var login = $( '#login' ).val();
	var password = $( '#password' ).val();
	$( '#loginfaild' ).remove();
	$( '#loginfaild-img' ).remove();
	
	if(login.length == 0) {
		$( '#loginform' ).prepend("<div id='loginfaild' class='login-element'></div>");
		$( '#loginfaild' ).append("<div id='loginfaild-img' class='loginfaild-img'></div>");
		$( '#loginfaild-img' ).append("<img src='/Sandals/resources/img/attention.png'>");
		$( '#loginfaild' ).append("<div class='loginfaild-text'>Введите логин</div>");
		
	} else {
		if(password.length == 0) {
			$( '#loginform' ).prepend("<div id='loginfaild' class='login-element'></div>");
			$( '#loginfaild' ).append("<div id='loginfaild-img' class='loginfaild-img'></div>");
			$( '#loginfaild-img' ).append("<img src='/Sandals/resources/img/attention.png'>");
			$( '#loginfaild' ).append("<div class='loginfaild-text'>Введите пароль</div>");			
		} else {
			document.getElementById('forms').submit();
		}
	}	
}