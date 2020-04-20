<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title> </title>
</head>
<body>
<Style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: centre;
  padding: 8px;
}

body {
	
	background-image: url("https://www.bigeyeagency.com/wp-content/uploads/2019/06/prescription-drug-ads-1200x600-c-default.jpg");
	background-size: 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

.right{
float:right;
}

</Style>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<center>
  <h3> The Medicine Shoppe</h3>
  <h4> Welcome ${customer.name}</h4>
  <hr>
<div class="right">
<a href="${contextPath}/custhome.htm">Home</a>  &nbsp; &nbsp; &nbsp;
 <a href="${contextPath}/viewCart.htm"> Cart</a>  &nbsp; &nbsp; &nbsp;
<a href="${contextPath}/logout.htm">Logout</a>&nbsp; &nbsp; &nbsp;
</div>
<br>
<hr>
<h4> Products List </h4>
 <form action="${contextPath}/searchProductCust.htm" method="post" id="searchform" name="searchform">
<input type="text" id="search" name="search" size= 40 placeholder="Search Product by Name"> &nbsp;&nbsp;&nbsp;
<button type="submit"> Search</button> <br><br>
</form>

<table class="table-bordered">
<tr>
<th>Product Name</th>
<th>Product Price $ </th>
<th> Status </th>
<th> Add to Cart </th>
</tr>
<c:forEach items = "${checkedList}" var ="prod">
         <tr>
         <td>${prod.productName}</td>
         <td>${prod.price}</td>
         
         <td>${prod.status}</td>
         <td>
         <c:choose>
         <c:when test="${prod.status == 'Out of Stock'}">
         <button disabled="disabled" type="submit" value="${prod.pid}" name="prod"> Add to Cart </button>
         </c:when>
         <c:when test="${prod.status == 'Available'}">
         <form action="${contextPath}/Addtocart.htm" method="post" id="cartform" name="cartform"> 
         <button type="submit" value="${prod.pid}" name="prod"> Add to Cart </button> </form> 
         </c:when>
         </c:choose>
         </td>
         
         </tr>
      </c:forEach>
</table>

<br><br>
<c:if test="${not empty errorMessage}">
  ${errorMessage}
</c:if>

<c:if test="${success==true}">
  <p> Product Added to Cart Successfully! </p>
</c:if>
   
</center>
</body>
</html>