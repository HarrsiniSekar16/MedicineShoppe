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
<a href="${contextPath}/viewProductsCust.htm">Products Page</a>  &nbsp; &nbsp; &nbsp;
<a href="${contextPath}/logout.htm">Logout</a>&nbsp; &nbsp; &nbsp;
</div>
<br>
<hr>
<h4> Cart </h4>
<br>
 
<script>
function Delete(){
	var x = confirm("Are you sure you want to remove the product from cart?");
	  if (x)
	      return true;
	  else
	    return false;
	}
</script>
<table class="table-bordered">
<tr>
<th>Product Name</th>
<th>Product Price $ </th>
<th> Quantity </th>
<th> Status </th>
<th> Remove Product </th>
<th> Total </th>
</tr>
<c:set var="total" value="0"></c:set>
<c:forEach items="${checkedList}" var="pro" >
<c:set var="total"
value="${total + pro.product.price * pro.quantity}"></c:set>
         <tr>
         <td>${pro.product.productName}</td>
         <td>${pro.product.price}</td>
         <td> <form action="${contextPath}/updateCart.htm" method="post" id="updateform" name="updateform"> 
         <input type="number" min= "1" size=5 value="${pro.quantity}" name="qty" required> 
         <input type="hidden" value="${pro.product.pid}" name="pid">
         &nbsp;&nbsp;&nbsp;<button type="submit" value="${pro.cartid}" name="prod"> Update Cart </button> </form>
         
         </td>
         <td>${pro.product.status}</td>
         <td> <form action="${contextPath}/removeCart.htm" method="post" id="deleteform" name="deleteform"> 
         <button type="submit" value="${pro.product.pid}" name="prod" onclick= "return Delete()"> Remove from cart </button> </form></td>
         <td>${pro.product.price * pro.quantity }</td>
         </tr>
      </c:forEach>
      <tr>
			<td colspan="5" align="right">Sum</td>
			<td>${total}</td>
		</tr>
</table>
<br><br>
<c:if test="${errormsg==true}" >
		 <p>Product running low.. Please limit your order to ${qty}</p>
	    </c:if>
	    <c:if test="${success==true}" >
		 <p>Product Updated Successfully</p>
	    </c:if>

<c:if test="${total == 0}">
<div class="right">
<form action="${contextPath}/checkout.htm" method="post" id="checkoutform" name="checkoutform">
<button type="submit" disabled="disabled" value="${pro}" name="prod"> Check Out </button> &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</form> 
</div>
</c:if>
<c:if test="${total > 0}">
<div class="right">
<form action="${contextPath}/checkout.htm" method="post" id="checkoutform" name="checkoutform">
<button type="submit" value="${pro}" name="prod"> CheckOut </button> &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</form> 
</div>
</c:if>
<c:if test="${error==true}" >
		<p> Product Deletion Failed! Try again later! </p>
	</c:if>
</center>
</body>
</html>