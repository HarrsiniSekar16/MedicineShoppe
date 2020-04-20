<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<body>
<Style>

body {
	
	background-image: url("https://www.bigeyeagency.com/wp-content/uploads/2019/06/prescription-drug-ads-1200x600-c-default.jpg");
	background-size: 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</Style>
<center>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <h3> The Medicine Shoppe</h3>
  <h4> Welcome ${admin.name }</h4>
  <hr>
  <a href="${contextPath}/registerAdminUser.htm">Register Admin</a> &nbsp; &nbsp; &nbsp;
  <a href="${contextPath}/registerProduct.htm">Register Product</a>  &nbsp; &nbsp; &nbsp;
  <a href="${contextPath}/viewProducts.htm"> Products Page </a>  &nbsp; &nbsp; &nbsp;
  <a href="${contextPath}/logout.htm">Logout</a>
<hr>
<br><br>

<c:if test="${success==true}" >
<p> Success! You are all set! </p>
</c:if>

<c:if test="${error==true}" >
<p> Product Deleted Successfully ! You are all set! </p>
</c:if>

</center>
</body>
</html>