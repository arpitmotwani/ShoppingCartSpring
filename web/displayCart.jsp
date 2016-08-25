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
<a href='/productList'><input type="submit" value="Add more Products"></a>

<c:choose>
    <c:when test="${cart.size()=='0'}">
        <br>
        <br>
        <span style="color:red">No Items available in the cart</span>
        <br />
        <br />
    </c:when>
    <c:otherwise>
        <a href='/placeOrder'><input type="submit" value="Place Order"></a>
        <br>
        <br>
        <table>
            <tr>
                <td>Product ID</td>
                <td>Product Name</td>
                <td>Product Price</td>
                <td>Product Stock</td>
                <td>Product Quantity</td>
            </tr>
            <c:forEach items="${cart}" var="item">
                <tr>
                    <td><c:out value="${item.productId}"></c:out></td>
                    <td><c:out value="${item.productName}"></c:out></td>
                    <td><c:out value="${item.productPrice}"></c:out></td>
                    <td><c:out value="${item.productStock}"></c:out></td>
                    <td><c:out value="${item.productQuantity}"></c:out></td>
                    <td><a href='/removeProduct?productId=${item.productId}'><input type="submit" value="Remove from Cart"/></a></td>
                </tr>
            </c:forEach>
        </table>

        <br />
    </c:otherwise>
</c:choose>
<a href="<c:url value="/logout"/>"><input type="submit" value="Logout"></a>
</body>
</html>