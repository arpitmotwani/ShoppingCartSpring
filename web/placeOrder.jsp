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
<h1>Your order has been placed successfully!</h1>
<a href='/productList'><input type="submit" value="Add New Products"></a>
<%--<a href='/productList?id=11112'>Add New Products</a>--%>
<br>
<br>
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <td>Product ID</td>
                    <td>Product Name</td>
                    <td>Product Price</td>
                    <%--<td>Product Quantity</td>--%>
                </tr>
                <c:forEach items="${cart}" var="item">
                    <tr>
                        <td><c:out value="${item.productId}"></c:out></td>
                        <td><c:out value="${item.productName}"></c:out></td>
                        <td><c:out value="${item.productPrice}"></c:out></td>
                            <%--<td><c:out value="${item.productQuantity}"></c:out></td>--%>
                    </tr>
                </c:forEach>

            </table>
        </td>
        <td>
            <table>
                <tr>
                    <td>Product Quantity</td>
                </tr>
                <c:forEach items="${quantity}" var="item">
                    <tr>
                        <td><c:out value="${item}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
<br>
<h2>
Total Amount : <c:out value="${totalAmount}"></c:out>
</h2>
<br>
<a href="<c:url value="/logout"/>"><input type="submit" value="Logout"></a>
</body>
</html>