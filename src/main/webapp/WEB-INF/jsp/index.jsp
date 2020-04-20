<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>The Medicine Shoppe</title>
</head>
<Style>

body {
	
	background-image: url("https://www.bigeyeagency.com/wp-content/uploads/2019/06/prescription-drug-ads-1200x600-c-default.jpg");
	background-size: 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</Style>

<body>
<center>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h3>The Medicine Shoppe </h3>
			<label> Customer Login </label>
			<br><br>
					<form action="${contextPath}/customerlogin.htm" method="post">
						<input type="email" id="email" placeholder="Please enter your email" name="customer-email" value="" size=40 required><br> <br>
							<input type="password" id="password"  placeholder="Please enter the Password" name="customer-password" value="" size=40 required><br><br> 
							<c:if test="${error==true}" >
								<p>User-Id or the Password is not valid</p>
							</c:if>
							<c:if test="${success==true}" >
								<p>Please login to proceed further</p>
							</c:if>
							<input id="submit" type="submit" name="submit" value="Login"><br><br>
							<a href="${contextPath}/adminlogin.htm">Login as Admin</a><br> 
							<a href="${contextPath}/registercustomer.htm">New Customer Registration?</a><br><br> 
							
					</form>
		</center>
		
		<c:if test="error">
		<script type="text/javascript">
			var userInput = document.getElementById("email");
			var userPassword = document.getElementById("password");
			userInput.setAttribute("value", "");
			userPassword.setAttribute("value", "");
			alert("Incorrect username and password, please check it again");
		</script>
	
	</c:if>
</body>
</html>
