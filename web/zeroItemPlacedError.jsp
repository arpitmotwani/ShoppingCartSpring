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
<h3 style="color: red">First add items to place order</h3>
<a href="<c:url value="/logout"/>"><input type="submit" value="Logout"></a>
</body>
</html>