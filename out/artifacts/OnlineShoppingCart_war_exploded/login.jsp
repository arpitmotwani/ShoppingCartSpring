<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="headerSection.jsp"></jsp:include>
<form:form action="/login" method="post" commandName="loginBean">
    <h2>Online Shopping Cart</h2>
    <table>
        <tr>
            <td><span class="red-text text-darken-2">
				<c:if test="${ not empty error }">Invalid Credentials</c:if>
				</span>
            </td>
        </tr>
        <tr>
            <td><form:label path="username">Enter Username&nbsp;</form:label></td>
            <td><form:input path="username"></form:input></td>
            <td><form:errors cssStyle="color: red" path="username"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Enter Password&nbsp;</form:label></td>
            <td><form:password path="password"></form:password></td>
            <td><form:errors cssStyle="color: red" path="password"/></td>
        </tr>
        <tr>
            <td><form:button value="loginButton">Login</form:button></td>
        </tr>
        <tr>
            <span style="color:red"><td><c:out value="${loginError}"/></td></span>
            <span style="color:red"><td><c:out value="${loginErrorDueToSession}"/></td></span>
        </tr>
    </table>
</form:form>
<jsp:include page="footerSection.jsp"></jsp:include>