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
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

  <h3> The Medicine Shoppe</h3>
  <h4> Welcome ${admin.name}</h4><br>
<hr>
<div class="right"> 
<a href="${contextPath}/home.htm">Home</a>  &nbsp; &nbsp; &nbsp;
<a href="${contextPath}/logout.htm">Logout</a>&nbsp; &nbsp; &nbsp;
</div>
<br>
<hr>

<h4>Product Info </h4>
<br>

     <form action="${contextPath}/updateproduct-submit.htm" method="post" id="registerform" name="registerform">

		Product Name : <input type="text" id="pname" name="pname" value="${pr.productName}" size=30 required /> 
		<br> <br>
		
	 	Price in $ : <input type="number" id="price" name="price"  value="${pr.price}" size=10  min="0" max="5000" required/> &nbsp; &nbsp; &nbsp;
		
		Quantity : <input type="number" name="quantity" id="quantity" value="${pr.quantity}" size=15  min="0" max="5000" required/>
		<br><br>
		
		<input type="hidden" value="${pr.pid}" name="id">
		<button type="submit"> Update Product </button> <br><br>

     </form> 
	
	<c:if test="${success==true}" >
		<p> Product Update Failed! Try again later! </p>
	</c:if>

</center>
</body>

</html>