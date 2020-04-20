<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>The Medicine Shoppe</title>
</head>

<body >
<Style>

body {
	
	background-image: url("https://www.bigeyeagency.com/wp-content/uploads/2019/06/prescription-drug-ads-1200x600-c-default.jpg");
	background-size: 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</Style>

<center> 

<h3>The Medicine Shoppe </h3>
			<label> Customer Registration </label>
			<br>
			<br>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form action="${contextPath}/registercustomer-submit.htm" method="post" id="customerform" name="customerform">


		<input type="text" name="name" id="name" placeholder="Please enter your Name" size=40 required/>
		<br><br>
		
		<input type="email" id="customer-email" size=40 name="customer-email" placeholder="Please enter your email" required /> 
		<br><br>
		
		<input type="password" id="customer-password" size=40 name="customer-password" placeholder="Please enter the password" required/>
		<br><br>
		
		Registering as : <input type="radio" name="role" id="role" value="customer" checked>
		<label> customer </label> <br><br>
		
		<button type="submit">Register</button> <br><br>
		
		<a href="${contextPath}/index.htm">Return to main Page</a>
</form> 
                            <c:if test="${error==true}" >
								<p> UserName already exists! Please try with different one</p>
							</c:if>

</center>
</body>
</html>

