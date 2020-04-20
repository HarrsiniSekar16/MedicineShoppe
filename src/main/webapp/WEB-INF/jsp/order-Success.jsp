<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.neu.model.Product" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>Order Review </title>
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

.left{
float:left;
}
</Style>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<center>
  <h3> The Medicine Shoppe</h3>
  <h4> Welcome ${customer.name}</h4>
 <hr>
 
<div class="left">
&nbsp; &nbsp; &nbsp; <a href="${contextPath}/report.pdf"> Generate Bill as PDF </a>  
</div>
<div class="right">
<a href="${contextPath}/custhome.htm">Home</a>  &nbsp; &nbsp; &nbsp;
  <a href="${contextPath}/viewCart.htm"> Cart</a>  &nbsp; &nbsp; &nbsp;
<a href="${contextPath}/logout.htm">Logout</a>&nbsp; &nbsp; &nbsp;
</div>
<br>
<hr>
<h4> Order Placed Successfully! </h4>
<br>
 
<table class="table-bordered">
<tr>
<th>Product Name</th>
<th>Product Price $ </th>
<th> Quantity </th>
<th> Total $</th>
</tr>
<c:set var="total" value="0"></c:set>
<c:forEach items="${checkedList}" var="pro" >
<c:set var="total"
value="${total + pro.product.price * pro.quantity}"></c:set>
         <tr>
         <td>${pro.product.productName}</td>
         <td>${pro.product.price}</td>
         <td>${pro.quantity}</td>
         <td>${pro.product.price * pro.quantity }</td>
         </tr>
      </c:forEach>
      <tr>
			<td colspan="3" align="right">Sum</td>
			<td>${total}</td>
		</tr>
</table>
<br><br>
<h4>Shipping Address</h4>
<hr>
<input type="text" name= "name" size=30 value="${customer.name}"  placeholder="Full Name" readonly="readonly"/>  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<input type="text" name="address" size=30 placeholder="Street Address" value="${address}" required readonly="readonly"/><br><br>
<input type="text" name="city" size=30 value="${city}" placeholder="City" required readonly="readonly"/>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<input type="text" name="state" size=30 value="${state}" placeholder="State" required readonly="readonly"/><br><br>
<input type="tel" name="phonenumber" size=30 value="${pnumber}" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
       required placeholder="Phone Number" readonly="readonly"/>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<input type="text" name="email" size=30 value="${email}" placeholder="E-Mail ID" required readonly="readonly"/><br><br>



</center>
</body>
</html>