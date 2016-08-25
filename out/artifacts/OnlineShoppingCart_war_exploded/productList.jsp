<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Products</title>
</head>
<body>
<h1>Products</h1>
<table>
    <tr>
        <td>Product ID</td>
        <td>Product Name</td>
        <td>Product Price</td>
        <td>Stock</td>
    </tr>
    <c:forEach items="${productList.getProducts()}" var="item">
        <tr>
            <td><c:out value="${item.getProductId()}"></c:out></td>
            <td><c:out value="${item.getProductName()}"></c:out></td>
            <td><c:out value="${item.getProductPrice()}"></c:out></td>
            <td><c:out value="${item.getProductStock()}"></c:out></td>
            <td><a href='/addProduct?productId=${item.getProductId()}'><input type="submit" value="Add to cart"/></a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="<c:url value="/displayCart"/>"><input type="submit" value="View cart"></a>
<a href="<c:url value="/logout"/>"><input type="submit" value="Logout"></a>
</body>
</html>