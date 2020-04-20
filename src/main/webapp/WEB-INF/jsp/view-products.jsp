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
  <h4> Welcome ${admin.name}</h4>
 <hr>
<div class="right">
<a href="${contextPath}/home.htm">Home</a>  &nbsp; &nbsp; &nbsp;
<a href="${contextPath}/logout.htm">Logout</a>&nbsp; &nbsp; &nbsp;
</div>
<br>
<hr>
<h4> Products List </h4>
 <form action="${contextPath}/searchProducts.htm" method="post" id="searchform" name="searchform">
 <input type="text" id="search" name="search" size=40 placeholder ="Search Product by Name"> &nbsp;&nbsp;&nbsp;
<button type="submit"> Search</button> <br><br>
</form>
<script>
function Delete(){
	var x = confirm("Are you sure you want to delete the product?");
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
<th> Update Product </th>
<th> Delete Product </th>
</tr>
<c:forEach items = "${checkedList}" var ="prod">
         <tr>
         <td>${prod.productName}</td>
         <td>${prod.price}</td>
         <td>${prod.quantity}</td>
         <td>${prod.status}</td>
         <td> <form action="${contextPath}/updateProduct.htm" method="post" id="updateform" name="updateform"> 
         <button type="submit" value="${prod.pid}" name="prod"> Update </button> </form></td>
         <td> <form action="${contextPath}/deleteProduct.htm" method="post" id="deleteform" name="deleteform"> 
         <button type="submit" value="${prod.pid}" name="product" onclick="return Delete()"> Delete </button> </form></td>
         </tr>
      </c:forEach>
</table>
<c:if test="${error==true}" >
		<p> Product Deletion Failed! Try again later! </p>
	</c:if>
</center>
</body>
</html>