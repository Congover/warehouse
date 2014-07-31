<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
		<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="resources/js/login.js"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>"/>
</head>
<body>
	<div class="container">
				<form class="form-signin" name='f' action="<c:url value='j_spring_security_check'/>" method='POST' id="forms">
				<div id="form-signin">							
					<c:if test="${not empty error}">
						<div id='loginfaild' class='login-element'>
							<div id='loginfaild-img' class='bg-image loginfaild-img'>
							</div>
							<div class='loginfaild-text'><spring:message code="login.faild" /></div>
						</div>
					</c:if>
					<div class="loginform-input">
						<div class="login-element">
							<div class="loginpage-left">
								<spring:message code="login.name" />
							</div>
							<div class="loginpage-right">
								<input class="login" type='text' maxlength="255" name='j_username' value='' id="login" />
							</div>
						</div>
						<div class="login-element">
							<div class="loginpage-left">
								<spring:message code="login.password" />
							</div>
							<div class="loginpage-right">
								<input class="login" type='password' maxlength="255" name='j_password' id="password"/>
							</div>
						</div>
					</div>
					<button class="btn btn-large btn-primary" type="submit" onclick="validateloginpage()">Sign in</button>
					<div class="login-button" onclick="validateloginpage()">
						<a href="#" type="submit" ></a>
					</div>
				</div>							
				</form>
	</div>
</body>
</html>