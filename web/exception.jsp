<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Exception/Error</title>
</head>
<body>

<c:out value="${exceptionMessage}"></c:out>
<br>
<a href="/productList">Products</a>
<%--<a href="<c:url value="/productList.jsp"/>">Products</a>--%>
</body>
</html>