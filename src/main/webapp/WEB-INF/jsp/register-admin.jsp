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
<body>
<Style>
.right{
float: right;
}
body {
	
	background-image: url("https://www.bigeyeagency.com/wp-content/uploads/2019/06/prescription-drug-ads-1200x600-c-default.jpg");
	background-size: 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</Style>
<center>

  <h3> The Medicine Shoppe</h3>
  <h4> Welcome ${admin.name}</h4>
<hr>  
<div class="right">
<a href="home.htm">Home</a>  &nbsp; &nbsp; &nbsp;
<a href="logout.htm">Logout</a>&nbsp; &nbsp; &nbsp;
</div>
<br>
<hr>
 
<h4> Admin Account Registration</h4>
<br>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
     <form action="${contextPath}/registeradmin-submit.htm" method="post" id="registerform" name="registerform">

		E-Mail ID : <input type="email" id="email" name="email" placeholder="@gmail.com" size=30 required /> 
		<br> <br>
		
		Password  : <input type="password" id="password" name="password" size=30  required/>
		<br><br>
		
		Full Name : <input type="text" name="name" id="name" size=30 required/>
		<br><br>
		
		Registering as: <input type="radio" name="role" id="role" value="admin" checked>
		<label> admin </label> <br><br>
		
		<button type="submit">Register</button> <br><br>

     </form> 
        <c:if test="${error==true}" >
								<p> UserName already exists! Please try with different one</p>
							</c:if>

</center>
</body>
</html>

