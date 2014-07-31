$(function() {
	var widthHeight = function(w, h) {
		if(h > w) {
			return w;
		} else {
			return h;
		}		
	};
	var updateIconsSize = function(){
		var count = $('.icons').length;
		if(count > 0) {
			var el = $('.icons').eq(0);
			var h = el.height();
			var w = el.width();
			var size = widthHeight(w, h);
			var m_left = ($('.l-panel').width() - size) / 2;
			var m_top = ($('.l-panel').height() - (size * count) ) / (count + 1);
			$('.icons').each(function(index, iconEl){
				$(iconEl).height(size);
				$(iconEl).width(size);
				$(iconEl).css('margin-top', m_top);
				$(iconEl).css('margin-left', m_left);
			});
		};
	};
	
	var updateSize = function() {
	var height = $('.h-title').height();
	$('.home-title-sandals').height($('.home-title-sandals').width() * 3 / 4);
	$('.home-title-mercury').height($('.home-title-mercury').width() * 66 / 191);
	$('.home-title').height( $('.home-title-sandals').height());
	$('.home-title-before').height( height - $('.home-title').height());
	$('.create-order').height( $('.create-order').width() * 40 / 109);
	
	var buttonSize = widthHeight($('.login-button').width(), $('.login-button').height());
	$('.login-button').height(buttonSize);
	$('.login-button').width(buttonSize);
	
	};
	
	$(window).resize(function() {
		updateIconsSize();
		updateSize();
	});
	updateSize();
	updateIconsSize();
});

function validateloginpage() {
	var login = $( '#login' ).val();
	var password = $( '#password' ).val();
	$( '#loginfaild' ).remove();
	
	if(login.length == 0) {
		$( '#loginform' ).prepend("<div id='loginfaild' class='login-element'></div>");
		$( '#loginfaild' ).append("<div id='loginfaild-img' class='bg-image loginfaild-img'></div>");
		$( '#loginfaild' ).append("<div class='loginfaild-text'>Введите логин</div>");
		
	} else {
		if(password.length == 0) {
			$( '#loginform' ).prepend("<div id='loginfaild' class='login-element'></div>");
			$( '#loginfaild' ).append("<div id='loginfaild-img' class='bg-image loginfaild-img'></div>");
			$( '#loginfaild-img' ).append("<img src='/Sandals/resources/img/attention.png'>");
			$( '#loginfaild' ).append("<div class='loginfaild-text'>Введите пароль</div>");			
		} else {
			document.getElementById('forms').submit();
		}
	}	
};

function link(str) {
	location.href = (str);
};