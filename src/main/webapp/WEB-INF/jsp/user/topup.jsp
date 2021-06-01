<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title><fmt:message key="user.menu.title"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf"%>
<div style="margin-left: 30px; color: darkgreen">
    <fmt:message key="user_menu.hello"/>, &nbsp<c:out value="${sessionScope.firstName}"/><br>
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message key="user_menu.UAH"/>
</div>

<form style="margin-left: 50px" id="settings_form" action="controller" method="get">
    <input type="hidden" name="command" value="popup">
    <input type="number" pattern="^\\d*" name="amount" class="col-sm-2">
    <button type="submit" class="btn btn-primary">Pay</button>
</form>

<c:out value="${sessionScope.errorMessage}"/>
</body>
</html>